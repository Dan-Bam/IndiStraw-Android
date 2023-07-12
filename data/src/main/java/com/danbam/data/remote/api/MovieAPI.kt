package com.danbam.data.remote.api

import com.danbam.data.remote.response.MoviePageResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET(EndPoint.Movie)
    suspend fun movieList(
        @Query("page") page: Int = 0,
        @Query("keyword") genre: String? = null
    ): MoviePageResponse
}