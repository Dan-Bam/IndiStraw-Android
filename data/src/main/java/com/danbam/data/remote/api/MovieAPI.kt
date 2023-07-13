package com.danbam.data.remote.api

import com.danbam.data.remote.request.MovieCreateRequest
import com.danbam.data.remote.response.MoviePageResponse
import com.danbam.data.remote.response.MoviePeopleResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @POST(EndPoint.Movie)
    suspend fun movieCreate(
        @Body movieCreateRequest: MovieCreateRequest
    ): Response<Void?>

    @GET(EndPoint.Movie)
    suspend fun movieList(
        @Query("page") page: Int = 1,
        @Query("keyword") genre: String? = null
    ): MoviePageResponse

    @GET("${EndPoint.Movie}/{actorType}")
    suspend fun searchMoviePeople(
        @Path("actorType") actorType: String,
        @Query("name") name: String,
    ): List<MoviePeopleResponse>
}