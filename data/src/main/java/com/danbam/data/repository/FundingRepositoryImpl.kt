package com.danbam.data.repository

import com.danbam.data.remote.datasource.FundingRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.domain.param.FundingParam
import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingRepositoryImpl @Inject constructor(
    private val fundingRemoteDataSource: FundingRemoteDataSource
) : FundingRepository {
    override suspend fun getReceipt(): String = fundingRemoteDataSource.getReceipt().receiptId
    override suspend fun funding(
        crowdfundingIdx: Long,
        rewardIdx: Long,
        fundingParam: FundingParam
    ) = fundingRemoteDataSource.funding(
        crowdfundingIdx = crowdfundingIdx,
        rewardIdx = rewardIdx,
        fundingRequest = fundingParam.toRequest()
    )
}