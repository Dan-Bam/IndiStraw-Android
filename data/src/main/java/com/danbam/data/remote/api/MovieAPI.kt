package com.danbam.data.remote.api

import com.danbam.data.remote.request.MovieHistoryRequest
import com.danbam.data.remote.request.MovieCreateRequest
import com.danbam.data.remote.request.MoviePeopleRequest
import com.danbam.data.remote.response.AddMoviePeopleResponse
import com.danbam.data.remote.response.DetailMovieHistoryResponse
import com.danbam.data.remote.response.MovieDetailResponse
import com.danbam.data.remote.response.MovieHistoryResponse
import com.danbam.data.remote.response.MoviePageResponse
import com.danbam.data.remote.response.MoviePeopleDetailResponse
import com.danbam.data.remote.response.MoviePeopleResponse
import com.danbam.data.remote.response.MovieResponse
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

    @GET("${EndPoint.Movie}/{movieId}/")
    suspend fun movieDetail(
        @Path("movieId") movieIdx: Long,
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
        @Path("idx") idx: Long,
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
    ): MovieHistoryResponse

    @GET("${EndPoint.Movie}/history/{movieIdx}/")
    suspend fun movieHistory(
        @Path("movieIdx") movieIdx: Long,
    ): DetailMovieHistoryResponse
}