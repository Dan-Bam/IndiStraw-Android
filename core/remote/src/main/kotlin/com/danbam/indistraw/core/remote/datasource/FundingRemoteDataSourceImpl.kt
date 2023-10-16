package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.api.FundingAPI
import com.danbam.indistraw.core.remote.request.funding.FundingRequest
import com.danbam.indistraw.core.remote.response.funding.FundingResponse
import com.danbam.indistraw.core.remote.response.funding.ReceiptResponse
import com.danbam.indistraw.core.remote.util.errorHandling
import com.danbam.indistraw.core.remote.util.indiStrawApiCall
import javax.inject.Inject

class FundingRemoteDataSourceImpl @Inject constructor(
    private val fundingAPI: FundingAPI
) : FundingRemoteDataSource {
    override suspend fun getReceipt(): ReceiptResponse =
        indiStrawApiCall {
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

    override suspend fun fundingList(): List<FundingResponse> =
        indiStrawApiCall {
            fundingAPI.fundingList()
        }
}