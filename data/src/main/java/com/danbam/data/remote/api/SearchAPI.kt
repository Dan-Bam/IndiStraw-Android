package com.danbam.data.remote.api

import com.danbam.data.remote.response.FundingPageResponse
import com.danbam.data.remote.response.MoviePageResponse
import com.danbam.data.remote.response.PopularTagResponse
import com.danbam.data.remote.response.RelatedSearchResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("${EndPoint.SEARCH}/")
    suspend fun gerRelatedSearch(
        @Query("keyword") keyword: String
    ): List<RelatedSearchResponse>

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