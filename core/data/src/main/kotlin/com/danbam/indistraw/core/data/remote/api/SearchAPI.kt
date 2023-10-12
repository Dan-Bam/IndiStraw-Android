package com.danbam.indistraw.core.data.remote.api

import com.danbam.indistraw.core.data.remote.response.funding.FundingPageResponse
import com.danbam.indistraw.core.data.remote.response.movie.MoviePageResponse
import com.danbam.indistraw.core.data.remote.response.search.PopularTagResponse
import com.danbam.indistraw.core.data.remote.util.EndPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("${EndPoint.SEARCH}/")
    suspend fun gerRelatedSearch(
        @Query("keyword") keyword: String
    ): List<String>

    @GET("${EndPoint.SEARCH}/movie/")
    suspend fun searchMovie(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("keyword") keyword: String,
    ): MoviePageResponse

    @GET("${EndPoint.SEARCH}/crowdfunding")
    suspend fun searchFunding(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("keyword") keyword: String,
    ): FundingPageResponse

    @GET("${EndPoint.SEARCH}/tag/")
    suspend fun popularTag(): PopularTagResponse
}