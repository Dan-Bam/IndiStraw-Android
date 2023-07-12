package com.danbam.data.remote.api

import com.danbam.data.remote.request.FundingRequest
import com.danbam.data.remote.response.ReceiptResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface FundingAPI {
    @POST("${EndPoint.FUNDING}/receipt")
    suspend fun getReceipt(): ReceiptResponse

    @POST("${EndPoint.FUNDING}/crowdfunding/{crowdfundingIdx}/reword/{rewordIdx}")
    suspend fun funding(
        @Path("crowdfundingIdx") crowdfundingIdx: Long,
        @Path("rewordIdx") rewardIdx: Long,
        @Body fundingRequest: FundingRequest
    ): Response<Void?>
}