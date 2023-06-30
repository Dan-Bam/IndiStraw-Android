package com.danbam.data.repository

import com.danbam.data.remote.datasource.FundingRemoteDataSource
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingRepositoryImpl @Inject constructor(
    private val fundingRemoteDataSource: FundingRemoteDataSource
) : FundingRepository {
    override suspend fun fundingPopularList(): List<FundingEntity> =
        fundingRemoteDataSource.fundingPopularList().map { it.toEntity() }
}