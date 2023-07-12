package com.danbam.data.remote.api

import com.danbam.data.remote.response.ReceiptResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.http.POST

interface FundingAPI {
    @POST("${EndPoint.FUNDING}/receipt")
    suspend fun getReceipt(): ReceiptResponse
}