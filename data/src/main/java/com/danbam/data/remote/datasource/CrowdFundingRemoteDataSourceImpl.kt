package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.CrowdFundingAPI
import com.danbam.data.remote.pagingsource.FundingPagingSource
import com.danbam.data.remote.request.FundingCreateRequest
import com.danbam.data.remote.response.FundingDetailResponse
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CrowdFundingRemoteDataSourceImpl @Inject constructor(
    private val crowdFundingAPI: CrowdFundingAPI
) : CrowdFundingRemoteDataSource {
    override suspend fun fundingCreate(fundingCreateRequest: FundingCreateRequest) =
        indiStrawApiCall {
            crowdFundingAPI.fundingCreate(fundingCreateRequest = fundingCreateRequest).errorHandling()
        }

    override suspend fun fundingPopularList(): List<FundingResponse> = indiStrawApiCall {
        crowdFundingAPI.fundingPopularList()
    }

    override suspend fun fundingDetail(fundingIndex: Long): FundingDetailResponse =
        indiStrawApiCall {
            crowdFundingAPI.fundingDetail(fundingIndex = fundingIndex)
        }

    override suspend fun fundingAll(): Flow<PagingData<FundingResponse>> =
        Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                FundingPagingSource(
                    crowdFundingAPI = crowdFundingAPI
                )
            }).flow
}