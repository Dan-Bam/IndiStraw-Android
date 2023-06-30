package com.danbam.data.remote.api

import com.danbam.data.remote.response.FundingResponse
import retrofit2.http.GET

interface FundingAPI {
    @GET("crowdfunding/popular/list")
    suspend fun fundingPopularList(): List<FundingResponse>
}