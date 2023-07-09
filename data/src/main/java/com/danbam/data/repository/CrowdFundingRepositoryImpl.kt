package com.danbam.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.data.remote.datasource.CrowdFundingRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.entity.FundingDetailEntity
import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.param.FundingCreateParam
import com.danbam.domain.repository.CrowdFundingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CrowdFundingRepositoryImpl @Inject constructor(
    private val crowdFundingRemoteDataSource: CrowdFundingRemoteDataSource
) : CrowdFundingRepository {
    override suspend fun fundingCreate(fundingCreateParam: FundingCreateParam) =
        crowdFundingRemoteDataSource.fundingCreate(fundingCreateRequest = fundingCreateParam.toRequest())

    override suspend fun fundingPopularList(): List<FundingEntity> =
        crowdFundingRemoteDataSource.fundingPopularList().map { it.toEntity() }

    override suspend fun fundingDetail(fundingIndex: Long): FundingDetailEntity =
        crowdFundingRemoteDataSource.fundingDetail(fundingIndex = fundingIndex).toEntity()

    override suspend fun fundingAll(): Flow<PagingData<FundingEntity>> =
        crowdFundingRemoteDataSource.fundingAll().map { it.map { it.toEntity() } }

    override suspend fun fundingMy(): List<FundingEntity> =
        crowdFundingRemoteDataSource.fundingMy().map { it.toEntity() }
}