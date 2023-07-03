package com.danbam.data.remote.api

import com.danbam.data.remote.response.RelatedSearchResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("${EndPoint.SEARCH}/")
    suspend fun gerRelatedSearch(
        @Query("keyword") keyword: String
    ): List<RelatedSearchResponse>
}