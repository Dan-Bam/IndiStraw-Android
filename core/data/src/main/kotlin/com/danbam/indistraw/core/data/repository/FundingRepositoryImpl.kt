package com.danbam.indistraw.core.data.repository

import com.danbam.indistraw.core.remote.datasource.FundingRemoteDataSource
import com.danbam.indistraw.core.remote.request.funding.toRequest
import com.danbam.indistraw.core.domain.entity.funding.FundingEntity
import com.danbam.indistraw.core.domain.param.funding.FundingParam
import com.danbam.indistraw.core.domain.repository.FundingRepository
import com.danbam.indistraw.core.remote.response.funding.toEntity
import javax.inject.Inject

class FundingRepositoryImpl @Inject constructor(
    private val fundingRemoteDataSource: FundingRemoteDataSource
) : FundingRepository {
    override suspend fun getReceipt(): String = fundingRemoteDataSource.getReceipt().receiptId
    override suspend fun funding(
        fundingIdx: Long,
        rewardIdx: Long,
        fundingParam: FundingParam
    ) = fundingRemoteDataSource.funding(
        fundingIdx = fundingIdx,
        rewardIdx = rewardIdx,
        fundingRequest = fundingParam.toRequest()
    )

    override suspend fun fundingList(): List<FundingEntity> =
        fundingRemoteDataSource.fundingList().map { it.toEntity() }
}