package com.danbam.indistraw.domain.repository

import com.danbam.indistraw.domain.entity.funding.FundingEntity
import com.danbam.indistraw.domain.param.funding.FundingParam

interface FundingRepository {
    suspend fun getReceipt(): String
    suspend fun funding(fundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam)
    suspend fun fundingList(): List<FundingEntity>
}