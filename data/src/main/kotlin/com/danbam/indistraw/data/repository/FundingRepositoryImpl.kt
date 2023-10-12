package com.danbam.indistraw.data.repository

import com.danbam.indistraw.data.remote.datasource.FundingRemoteDataSource
import com.danbam.indistraw.data.remote.request.funding.toRequest
import com.danbam.indistraw.data.remote.response.funding.toEntity
import com.danbam.indistraw.domain.entity.funding.FundingEntity
import com.danbam.indistraw.domain.param.funding.FundingParam
import com.danbam.indistraw.domain.repository.FundingRepository
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