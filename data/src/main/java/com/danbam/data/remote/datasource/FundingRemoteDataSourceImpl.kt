package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.FundingAPI
import com.danbam.data.remote.request.FundingCreateRequest
import com.danbam.data.remote.response.FundingDetailResponse
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class FundingRemoteDataSourceImpl @Inject constructor(
    private val fundingAPI: FundingAPI
) : FundingRemoteDataSource {
    override suspend fun fundingCreate(fundingCreateRequest: FundingCreateRequest) =
        indiStrawApiCall {
            fundingAPI.fundingCreate(fundingCreateRequest = fundingCreateRequest).errorHandling()
        }

    override suspend fun fundingPopularList(): List<FundingResponse> = indiStrawApiCall {
        fundingAPI.fundingPopularList()
    }

    override suspend fun fundingDetail(fundingIndex: Long): FundingDetailResponse =
        indiStrawApiCall {
            fundingAPI.fundingDetail(fundingIndex = fundingIndex)
        }
}