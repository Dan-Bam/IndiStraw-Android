package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.FundingAPI
import com.danbam.data.remote.request.FundingRequest
import com.danbam.data.remote.response.ReceiptResponse
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
        crowdfundingIdx: Long,
        rewardIdx: Long,
        fundingRequest: FundingRequest
    ) = indiStrawApiCall {
        fundingAPI.funding(
            crowdfundingIdx = crowdfundingIdx,
            rewardIdx = rewardIdx,
            fundingRequest = fundingRequest
        ).errorHandling()
    }
}