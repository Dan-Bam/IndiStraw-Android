package com.danbam.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danbam.data.remote.api.SearchAPI
import com.danbam.data.remote.response.RelatedSearchResponse
import javax.inject.Inject

class RelatedSearchPagingSource(
    @Inject private val searchAPI: SearchAPI,
    private val keyword: String,
) : PagingSource<Int, RelatedSearchResponse.RelatedSearchItem>() {
    override fun getRefreshKey(state: PagingState<Int, RelatedSearchResponse.RelatedSearchItem>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RelatedSearchResponse.RelatedSearchItem> {
        return try {
            val page = params.key ?: 1
            val response = searchAPI.gerRelatedSearch(keyword = keyword)

            LoadResult.Page(
                data = response.searchList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isLast) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}