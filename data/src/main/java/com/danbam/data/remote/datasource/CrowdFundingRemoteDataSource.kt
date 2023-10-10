package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.request.funding.FundingCreateRequest
import com.danbam.data.remote.response.funding.FundingDetailResponse
import com.danbam.data.remote.response.funding.FundingResponse
import com.danbam.data.remote.response.funding.MyFundingResponse
import kotlinx.coroutines.flow.Flow

interface CrowdFundingRemoteDataSource {
    suspend fun fundingCreate(fundingCreateRequest: FundingCreateRequest)
    suspend fun fundingPopularList(): List<FundingResponse>
    suspend fun fundingDetail(fundingIdx: Long): FundingDetailResponse
    suspend fun fundingAll(): Flow<PagingData<FundingResponse>>
    suspend fun fundingMy(): List<FundingResponse>
    suspend fun fundingMyDetail(fundingIdx: Long): MyFundingResponse
}