package com.danbam.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danbam.data.remote.api.MovieAPI
import com.danbam.data.remote.response.movie.MovieResponse
import javax.inject.Inject

class MoviePagingSource(
    @Inject private val movieAPI: MovieAPI,
    private val genre: String? = null,
) : PagingSource<Int, MovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        return try {
            val page = params.key ?: 0
            val response = movieAPI.movieList(page = page, genre = genre)

            LoadResult.Page(
                data = response.list,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isLast) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}