package com.danbam.data.remote.datasource

import com.danbam.data.remote.response.FundingDetailResponse
import com.danbam.data.remote.response.FundingResponse

interface FundingRemoteDataSource {
    suspend fun fundingPopularList(): List<FundingResponse>
    suspend fun fundingDetail(fundingIndex: Long): FundingDetailResponse
}