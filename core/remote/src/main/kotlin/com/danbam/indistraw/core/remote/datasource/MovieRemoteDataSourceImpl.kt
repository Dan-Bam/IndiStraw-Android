package com.danbam.indistraw.core.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.indistraw.core.remote.api.MovieAPI
import com.danbam.indistraw.core.remote.pagingsource.MoviePagingSource
import com.danbam.indistraw.core.remote.request.movie.MovieCreateRequest
import com.danbam.indistraw.core.remote.request.movie.MovieHistoryRequest
import com.danbam.indistraw.core.remote.request.movie.MoviePeopleRequest
import com.danbam.indistraw.core.remote.response.movie.DetailMovieHistoryResponse
import com.danbam.indistraw.core.remote.response.movie.MovieDetailResponse
import com.danbam.indistraw.core.remote.response.movie.MovieHistoryResponse
import com.danbam.indistraw.core.remote.response.movie.MoviePeopleDetailResponse
import com.danbam.indistraw.core.remote.response.movie.MoviePeopleResponse
import com.danbam.indistraw.core.remote.response.movie.MovieResponse
import com.danbam.indistraw.core.remote.util.errorHandling
import com.danbam.indistraw.core.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieAPI: MovieAPI
) : MovieRemoteDataSource {
    override suspend fun movieCreate(movieCreateRequest: MovieCreateRequest) =
        indiStrawApiCall {
            movieAPI.movieCreate(movieCreateRequest = movieCreateRequest).errorHandling()
        }

    override suspend fun movieList(genre: String?): Flow<PagingData<MovieResponse>> =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviePagingSource(
                    movieAPI = movieAPI,
                    genre = genre
                )
            }).flow

    override suspend fun movieDetail(movieIdx: Long): MovieDetailResponse =
        indiStrawApiCall {
            movieAPI.movieDetail(movieIdx = movieIdx)
        }

    override suspend fun searchMoviePeople(
        actorType: String,
        name: String
    ): List<MoviePeopleResponse> = indiStrawApiCall {
        movieAPI.searchMoviePeople(actorType = actorType, name = name)
    }

    override suspend fun addMoviePeople(actorType: String, moviePeopleRequest: MoviePeopleRequest) =
        indiStrawApiCall {
            movieAPI.addMoviePeople(actorType = actorType, moviePeopleRequest = moviePeopleRequest)
        }

    override suspend fun moviePeopleDetail(
        actorType: String,
        actorIdx: Long
    ): MoviePeopleDetailResponse =
        indiStrawApiCall {
            movieAPI.moviePeopleDetail(actorType = actorType, actorIdx = actorIdx)
        }

    override suspend fun movieRecentList(): List<MovieResponse> =
        indiStrawApiCall {
            movieAPI.movieList().list.let {
                it.slice(0 until if (it.size <= 10) it.size else 10)
            }
        }

    override suspend fun moviePopularList(): List<MovieResponse> =
        indiStrawApiCall {
            movieAPI.moviePopularList()
        }

    override suspend fun movieRecommendList(): List<MovieResponse> =
        indiStrawApiCall {
            movieAPI.movieRecommendList()
        }

    override suspend fun movieHistoryList(): List<MovieHistoryResponse> =
        indiStrawApiCall {
            movieAPI.movieHistoryList()
        }

    override suspend fun addMovieHistory(movieHistoryRequest: MovieHistoryRequest) =
        indiStrawApiCall {
            movieAPI.addMovieHistory(movieHistoryRequest = movieHistoryRequest).errorHandling()
        }

    override suspend fun movieHistory(movieIdx: Long): DetailMovieHistoryResponse =
        indiStrawApiCall {
            movieAPI.movieHistory(movieIdx = movieIdx)
        }
}