package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.request.FundingCreateRequest
import com.danbam.data.remote.response.FundingDetailResponse
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.response.MyFundingResponse
import kotlinx.coroutines.flow.Flow

interface CrowdFundingRemoteDataSource {
    suspend fun fundingCreate(fundingCreateRequest: FundingCreateRequest)
    suspend fun fundingPopularList(): List<FundingResponse>
    suspend fun fundingDetail(fundingIndex: Long): FundingDetailResponse
    suspend fun fundingAll(): Flow<PagingData<FundingResponse>>
    suspend fun fundingMy(): List<FundingResponse>
    suspend fun fundingMyDetail(crowdfundingIdx: Long): MyFundingResponse
}