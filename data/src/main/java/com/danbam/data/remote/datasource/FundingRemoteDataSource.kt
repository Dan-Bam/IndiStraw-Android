package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.FundingRequest
import com.danbam.data.remote.response.ReceiptResponse

interface FundingRemoteDataSource {
    suspend fun getReceipt(): ReceiptResponse
    suspend fun funding(crowdfundingIdx: Long, rewardIdx: Long, fundingRequest: FundingRequest)
}