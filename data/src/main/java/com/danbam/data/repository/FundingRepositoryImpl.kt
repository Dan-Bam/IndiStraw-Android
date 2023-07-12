package com.danbam.data.repository

import com.danbam.data.remote.datasource.FundingRemoteDataSource
import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingRepositoryImpl @Inject constructor(
    private val fundingRemoteDataSource: FundingRemoteDataSource
) : FundingRepository {
    override suspend fun getReceipt(): String = fundingRemoteDataSource.getReceipt().receiptId
}