package com.danbam.indistraw.data.remote.datasource

import com.danbam.indistraw.data.remote.request.funding.FundingRequest
import com.danbam.indistraw.data.remote.response.funding.FundingResponse
import com.danbam.indistraw.data.remote.response.funding.ReceiptResponse

interface FundingRemoteDataSource {
    suspend fun getReceipt(): ReceiptResponse
    suspend fun funding(fundingIdx: Long, rewardIdx: Long, fundingRequest: FundingRequest)
    suspend fun fundingList(): List<FundingResponse>
}