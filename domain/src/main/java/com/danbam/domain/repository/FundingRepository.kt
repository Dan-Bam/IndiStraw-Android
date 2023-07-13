package com.danbam.domain.repository

import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.param.FundingParam

interface FundingRepository {
    suspend fun getReceipt(): String
    suspend fun funding(crowdfundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam)
    suspend fun fundingList(): List<FundingEntity>
}