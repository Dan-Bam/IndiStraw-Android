package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.funding.FundingRequest
import com.danbam.data.remote.response.funding.FundingResponse
import com.danbam.data.remote.response.funding.ReceiptResponse

interface FundingRemoteDataSource {
    suspend fun getReceipt(): ReceiptResponse
    suspend fun funding(fundingIdx: Long, rewardIdx: Long, fundingRequest: FundingRequest)
    suspend fun fundingList(): List<FundingResponse>
}