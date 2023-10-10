package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.request.movie.MovieCreateRequest
import com.danbam.data.remote.request.movie.MovieHistoryRequest
import com.danbam.data.remote.request.movie.MoviePeopleRequest
import com.danbam.data.remote.response.movie.AddMoviePeopleResponse
import com.danbam.data.remote.response.movie.DetailMovieHistoryResponse
import com.danbam.data.remote.response.movie.MovieDetailResponse
import com.danbam.data.remote.response.movie.MovieHistoryResponse
import com.danbam.data.remote.response.movie.MoviePeopleDetailResponse
import com.danbam.data.remote.response.movie.MoviePeopleResponse
import com.danbam.data.remote.response.movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun movieCreate(movieCreateRequest: MovieCreateRequest)
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieResponse>>
    suspend fun movieDetail(movieIdx: Long): MovieDetailResponse
    suspend fun searchMoviePeople(actorType: String, name: String): List<MoviePeopleResponse>
    suspend fun moviePeopleDetail(actorType: String, actorIdx: Long): MoviePeopleDetailResponse
    suspend fun addMoviePeople(
        actorType: String,
        moviePeopleRequest: MoviePeopleRequest
    ): AddMoviePeopleResponse

    suspend fun movieRecentList(): List<MovieResponse>
    suspend fun moviePopularList(): List<MovieResponse>
    suspend fun movieRecommendList(): List<MovieResponse>

    suspend fun movieHistoryList(): List<MovieHistoryResponse>
    suspend fun addMovieHistory(movieHistoryRequest: MovieHistoryRequest)
    suspend fun movieHistory(movieIdx: Long): DetailMovieHistoryResponse
}