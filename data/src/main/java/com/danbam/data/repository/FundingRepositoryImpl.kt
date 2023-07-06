package com.danbam.data.repository

import com.danbam.data.remote.datasource.FundingRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.entity.FundingDetailEntity
import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.param.FundingCreateParam
import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingRepositoryImpl @Inject constructor(
    private val fundingRemoteDataSource: FundingRemoteDataSource
) : FundingRepository {
    override suspend fun fundingCreate(fundingCreateParam: FundingCreateParam) =
        fundingRemoteDataSource.fundingCreate(fundingCreateRequest = fundingCreateParam.toRequest())

    override suspend fun fundingPopularList(): List<FundingEntity> =
        fundingRemoteDataSource.fundingPopularList().map { it.toEntity() }

    override suspend fun fundingDetail(fundingIndex: Long): FundingDetailEntity =
        fundingRemoteDataSource.fundingDetail(fundingIndex = fundingIndex).toEntity()
}