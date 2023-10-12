package com.danbam.indistraw.core.domain.repository

import com.danbam.indistraw.core.entity.funding.FundingEntity
import com.danbam.indistraw.core.param.funding.FundingParam

interface FundingRepository {
    suspend fun getReceipt(): String
    suspend fun funding(fundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam)
    suspend fun fundingList(): List<FundingEntity>
}