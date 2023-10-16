package com.danbam.indistraw.core.remote.api

import com.danbam.indistraw.core.remote.request.funding.FundingRequest
import com.danbam.indistraw.core.remote.response.funding.FundingResponse
import com.danbam.indistraw.core.remote.response.funding.ReceiptResponse
import com.danbam.indistraw.core.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FundingAPI {
    @POST("${EndPoint.FUNDING}/receipt")
    suspend fun getReceipt(): ReceiptResponse

    @POST("${EndPoint.FUNDING}/crowdfunding/{crowdfundingIdx}/reword/{rewordIdx}")
    suspend fun funding(
        @Path("crowdfundingIdx") fundingIdx: Long,
        @Path("rewordIdx") rewardIdx: Long,
        @Body fundingRequest: FundingRequest
    ): Response<Void?>

    @GET("${EndPoint.FUNDING}/my")
    suspend fun fundingList(): List<FundingResponse>
}