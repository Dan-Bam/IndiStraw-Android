package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.FundingAPI
import com.danbam.data.remote.request.funding.FundingRequest
import com.danbam.data.remote.response.funding.FundingResponse
import com.danbam.data.remote.response.funding.ReceiptResponse
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class FundingRemoteDataSourceImpl @Inject constructor(
    private val fundingAPI: FundingAPI
) : FundingRemoteDataSource {
    override suspend fun getReceipt(): ReceiptResponse = indiStrawApiCall {
        fundingAPI.getReceipt()
    }

    override suspend fun funding(
        fundingIdx: Long,
        rewardIdx: Long,
        fundingRequest: FundingRequest
    ) = indiStrawApiCall {
        fundingAPI.funding(
            fundingIdx = fundingIdx,
            rewardIdx = rewardIdx,
            fundingRequest = fundingRequest
        ).errorHandling()
    }

    override suspend fun fundingList(): List<FundingResponse> = indiStrawApiCall {
        fundingAPI.fundingList()
    }
}