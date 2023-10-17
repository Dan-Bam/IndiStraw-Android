package com.danbam.indistraw.core.remote.datasource

import androidx.paging.PagingData
import com.danbam.indistraw.core.remote.request.movie.MovieCreateRequest
import com.danbam.indistraw.core.remote.request.movie.MovieHistoryRequest
import com.danbam.indistraw.core.remote.request.movie.MoviePeopleRequest
import com.danbam.indistraw.core.remote.response.movie.AddMoviePeopleResponse
import com.danbam.indistraw.core.remote.response.movie.DetailMovieHistoryResponse
import com.danbam.indistraw.core.remote.response.movie.MovieDetailResponse
import com.danbam.indistraw.core.remote.response.movie.MovieHistoryResponse
import com.danbam.indistraw.core.remote.response.movie.MoviePeopleDetailResponse
import com.danbam.indistraw.core.remote.response.movie.MoviePeopleResponse
import com.danbam.indistraw.core.remote.response.movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun movieCreate(movieCreateRequest: MovieCreateRequest)
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieResponse>>
    suspend fun movieDetail(movieIdx: Long): MovieDetailResponse
    suspend fun searchMoviePeople(peopleType: String, name: String): List<MoviePeopleResponse>
    suspend fun moviePeopleDetail(peopleType: String, actorIdx: Long): MoviePeopleDetailResponse
    suspend fun addMoviePeople(
        peopleType: String,
        moviePeopleRequest: MoviePeopleRequest
    ): AddMoviePeopleResponse

    suspend fun movieRecentList(): List<MovieResponse>
    suspend fun moviePopularList(): List<MovieResponse>
    suspend fun movieRecommendList(): List<MovieResponse>

    suspend fun movieHistoryList(): List<MovieHistoryResponse>
    suspend fun addMovieHistory(movieHistoryRequest: MovieHistoryRequest)
    suspend fun movieHistory(movieIdx: Long): DetailMovieHistoryResponse

    suspend fun movieFilmography(): List<MovieResponse>
}