package com.danbam.domain.repository

import com.danbam.domain.entity.funding.FundingEntity
import com.danbam.domain.param.funding.FundingParam

interface FundingRepository {
    suspend fun getReceipt(): String
    suspend fun funding(fundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam)
    suspend fun fundingList(): List<FundingEntity>
}