package com.danbam.domain.repository

import androidx.paging.PagingData
import com.danbam.domain.entity.FundingDetailEntity
import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.param.FundingCreateParam
import kotlinx.coroutines.flow.Flow

interface CrowdFundingRepository {
    suspend fun fundingCreate(fundingCreateParam: FundingCreateParam)
    suspend fun fundingPopularList(): List<FundingEntity>
    suspend fun fundingDetail(fundingIndex: Long): FundingDetailEntity
    suspend fun fundingAll(): Flow<PagingData<FundingEntity>>
}