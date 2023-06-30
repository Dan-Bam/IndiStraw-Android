package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.FundingAPI
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class FundingRemoteDataSourceImpl @Inject constructor(
    private val fundingAPI: FundingAPI
) : FundingRemoteDataSource {
    override suspend fun fundingPopularList(): List<FundingResponse> = indiStrawApiCall {
        fundingAPI.fundingPopularList()
    }
}