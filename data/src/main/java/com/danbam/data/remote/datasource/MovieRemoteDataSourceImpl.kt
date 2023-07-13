package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.MovieAPI
import com.danbam.data.remote.pagingsource.MoviePagingSource
import com.danbam.data.remote.request.MovieCreateRequest
import com.danbam.data.remote.request.MovieHistoryRequest
import com.danbam.data.remote.request.MoviePeopleRequest
import com.danbam.data.remote.response.DetailMovieHistoryResponse
import com.danbam.data.remote.response.MoviePeopleDetailResponse
import com.danbam.data.remote.response.MovieDetailResponse
import com.danbam.data.remote.response.MovieHistoryResponse
import com.danbam.data.remote.response.MoviePeopleResponse
import com.danbam.data.remote.response.MovieResponse
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieAPI: MovieAPI
) : MovieRemoteDataSource {
    override suspend fun movieCreate(movieCreateRequest: MovieCreateRequest) = indiStrawApiCall {
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

    override suspend fun movieDetail(movieIdx: Int): MovieDetailResponse = indiStrawApiCall {
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

    override suspend fun moviePeopleDetail(actorType: String, idx: Int): MoviePeopleDetailResponse =
        indiStrawApiCall {
            movieAPI.moviePeopleDetail(actorType = actorType, idx = idx)
        }

    override suspend fun movieRecentList(): List<MovieResponse> = indiStrawApiCall {
        movieAPI.movieList().list.slice(0..10)
    }

    override suspend fun moviePopularList(): List<MovieResponse> = indiStrawApiCall {
        movieAPI.moviePopularList()
    }

    override suspend fun movieRecommendList(): List<MovieResponse> = indiStrawApiCall {
        movieAPI.movieRecommendList()
    }

    override suspend fun movieHistoryList(): List<MovieHistoryResponse> = indiStrawApiCall {
        movieAPI.movieHistoryList()
    }

    override suspend fun addMovieHistory(movieHistoryRequest: MovieHistoryRequest): MovieHistoryResponse =
        indiStrawApiCall {
            movieAPI.addMovieHistory(movieHistoryRequest = movieHistoryRequest)
        }

    override suspend fun movieHistory(movieIdx: Int): DetailMovieHistoryResponse =
        indiStrawApiCall {
            movieAPI.movieHistory(movieIdx = movieIdx)
        }
}