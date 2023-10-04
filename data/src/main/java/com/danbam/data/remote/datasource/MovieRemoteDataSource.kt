package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.request.MovieCreateRequest
import com.danbam.data.remote.request.MovieHistoryRequest
import com.danbam.data.remote.request.MoviePeopleRequest
import com.danbam.data.remote.response.AddMoviePeopleResponse
import com.danbam.data.remote.response.DetailMovieHistoryResponse
import com.danbam.data.remote.response.MovieDetailResponse
import com.danbam.data.remote.response.MovieHistoryResponse
import com.danbam.data.remote.response.MoviePeopleDetailResponse
import com.danbam.data.remote.response.MoviePeopleResponse
import com.danbam.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun movieCreate(movieCreateRequest: MovieCreateRequest)
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieResponse>>
    suspend fun movieDetail(movieIdx: Long): MovieDetailResponse
    suspend fun searchMoviePeople(actorType: String, name: String): List<MoviePeopleResponse>
    suspend fun moviePeopleDetail(actorType: String, idx: Long): MoviePeopleDetailResponse
    suspend fun addMoviePeople(
        actorType: String,
        moviePeopleRequest: MoviePeopleRequest
    ): AddMoviePeopleResponse

    suspend fun movieRecentList(): List<MovieResponse>
    suspend fun moviePopularList(): List<MovieResponse>
    suspend fun movieRecommendList(): List<MovieResponse>

    suspend fun movieHistoryList(): List<MovieHistoryResponse>
    suspend fun addMovieHistory(movieHistoryRequest: MovieHistoryRequest): MovieHistoryResponse
    suspend fun movieHistory(movieIdx: Long): DetailMovieHistoryResponse
}