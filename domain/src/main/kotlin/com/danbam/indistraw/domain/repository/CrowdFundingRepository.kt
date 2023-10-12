package com.danbam.indistraw.domain.repository

import androidx.paging.PagingData
import com.danbam.indistraw.domain.entity.funding.FundingDetailEntity
import com.danbam.indistraw.domain.entity.funding.FundingEntity
import com.danbam.indistraw.domain.entity.funding.MyFundingEntity
import com.danbam.indistraw.domain.param.funding.FundingCreateParam
import kotlinx.coroutines.flow.Flow

interface CrowdFundingRepository {
    suspend fun fundingCreate(fundingCreateParam: FundingCreateParam)
    suspend fun fundingPopularList(): List<FundingEntity>
    suspend fun fundingDetail(fundingIdx: Long): FundingDetailEntity
    suspend fun fundingAll(): Flow<PagingData<FundingEntity>>
    suspend fun fundingMy(): List<FundingEntity>
    suspend fun fundingMyDetail(fundingIdx: Long): MyFundingEntity
}