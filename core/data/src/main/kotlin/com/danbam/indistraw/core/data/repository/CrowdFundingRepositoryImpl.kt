package com.danbam.indistraw.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.indistraw.core.domain.entity.funding.FundingDetailEntity
import com.danbam.indistraw.core.domain.entity.funding.FundingEntity
import com.danbam.indistraw.core.domain.entity.funding.MyFundingEntity
import com.danbam.indistraw.core.domain.param.funding.FundingCreateParam
import com.danbam.indistraw.core.remote.datasource.CrowdFundingRemoteDataSource
import com.danbam.indistraw.core.remote.request.funding.toRequest
import com.danbam.indistraw.core.domain.repository.CrowdFundingRepository
import com.danbam.indistraw.core.remote.response.funding.toEntity
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

    override suspend fun fundingDetail(fundingIdx: Long): FundingDetailEntity =
        crowdFundingRemoteDataSource.fundingDetail(fundingIdx = fundingIdx).toEntity()

    override suspend fun fundingAll(): Flow<PagingData<FundingEntity>> =
        crowdFundingRemoteDataSource.fundingAll().map { it.map { it.toEntity() } }

    override suspend fun fundingMy(): List<FundingEntity> =
        crowdFundingRemoteDataSource.fundingMy().map { it.toEntity() }

    override suspend fun fundingMyDetail(fundingIdx: Long): MyFundingEntity =
        crowdFundingRemoteDataSource.fundingMyDetail(fundingIdx = fundingIdx).toEntity()
}