package com.danbam.domain.repository

import com.danbam.domain.entity.FundingEntity

interface FundingRepository {
    suspend fun fundingPopularList(): List<FundingEntity>
}