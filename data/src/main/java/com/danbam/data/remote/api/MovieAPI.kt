package com.danbam.data.remote.api

import com.danbam.data.remote.request.movie.MovieHistoryRequest
import com.danbam.data.remote.request.movie.MovieCreateRequest
import com.danbam.data.remote.request.movie.MoviePeopleRequest
import com.danbam.data.remote.response.movie.AddMoviePeopleResponse
import com.danbam.data.remote.response.movie.DetailMovieHistoryResponse
import com.danbam.data.remote.response.movie.MovieDetailResponse
import com.danbam.data.remote.response.movie.MovieHistoryResponse
import com.danbam.data.remote.response.movie.MoviePageResponse
import com.danbam.data.remote.response.movie.MoviePeopleDetailResponse
import com.danbam.data.remote.response.movie.MoviePeopleResponse
import com.danbam.data.remote.response.movie.MovieResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @POST("${EndPoint.Movie}/")
    suspend fun movieCreate(
        @Body movieCreateRequest: MovieCreateRequest
    ): Response<Void?>

    @GET("${EndPoint.Movie}/")
    suspend fun movieList(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("keyword") genre: String? = null
    ): MoviePageResponse

    @GET("${EndPoint.Movie}/{idx}/")
    suspend fun movieDetail(
        @Path("idx") movieIdx: Long,
    ): MovieDetailResponse

    @GET("${EndPoint.Movie}/{actorType}/")
    suspend fun searchMoviePeople(
        @Path("actorType") actorType: String,
        @Query("name") name: String,
    ): List<MoviePeopleResponse>

    @POST("${EndPoint.Movie}/{actorType}/")
    suspend fun addMoviePeople(
        @Path("actorType") actorType: String,
        @Body moviePeopleRequest: MoviePeopleRequest
    ): AddMoviePeopleResponse

    @GET("${EndPoint.Movie}/{actorType}/{idx}/")
    suspend fun moviePeopleDetail(
        @Path("actorType") actorType: String,
        @Path("idx") actorIdx: Long,
    ): MoviePeopleDetailResponse

    @GET("${EndPoint.Movie}/popular/")
    suspend fun moviePopularList(): List<MovieResponse>

    @GET("${EndPoint.Movie}/recommend/")
    suspend fun movieRecommendList(): List<MovieResponse>

    @GET("${EndPoint.Movie}/history/")
    suspend fun movieHistoryList(): List<MovieHistoryResponse>

    @POST("${EndPoint.Movie}/history/")
    suspend fun addMovieHistory(
        @Body movieHistoryRequest: MovieHistoryRequest
    ): Response<Void?>

    @GET("${EndPoint.Movie}/history/{idx}/")
    suspend fun movieHistory(
        @Path("idx") movieIdx: Long,
    ): DetailMovieHistoryResponse
}