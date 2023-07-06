package com.danbam.domain.repository

import com.danbam.domain.entity.FundingDetailEntity
import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.param.FundingCreateParam

interface FundingRepository {
    suspend fun fundingCreate(fundingCreateParam: FundingCreateParam)
    suspend fun fundingPopularList(): List<FundingEntity>
    suspend fun fundingDetail(fundingIndex: Long): FundingDetailEntity
}