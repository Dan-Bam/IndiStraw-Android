package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.FundingAPI
import javax.inject.Inject

class FundingRemoteDataSourceImpl @Inject constructor(
    private val fundingAPI: FundingAPI
) : FundingRemoteDataSource {
}