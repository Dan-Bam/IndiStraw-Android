package com.danbam.data.remote.api

import com.danbam.data.remote.response.FundingDetailResponse
import com.danbam.data.remote.response.FundingResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FundingAPI {
    @GET("crowdfunding/popular/list")
    suspend fun fundingPopularList(): List<FundingResponse>

    @GET("crowdfunding/{idx}")
    suspend fun fundingDetail(
        @Path("idx") fundingIndex: Long
    ): FundingDetailResponse
}