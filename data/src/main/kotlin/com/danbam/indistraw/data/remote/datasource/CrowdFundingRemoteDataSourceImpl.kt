package com.danbam.indistraw.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.indistraw.data.remote.api.CrowdFundingAPI
import com.danbam.indistraw.data.remote.pagingsource.FundingPagingSource
import com.danbam.indistraw.data.remote.request.funding.FundingCreateRequest
import com.danbam.indistraw.data.remote.response.funding.FundingDetailResponse
import com.danbam.indistraw.data.remote.response.funding.FundingResponse
import com.danbam.indistraw.data.remote.response.funding.MyFundingResponse
import com.danbam.indistraw.data.remote.util.errorHandling
import com.danbam.indistraw.data.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CrowdFundingRemoteDataSourceImpl @Inject constructor(
    private val crowdFundingAPI: CrowdFundingAPI
) : CrowdFundingRemoteDataSource {
    override suspend fun fundingCreate(fundingCreateRequest: FundingCreateRequest) =
        indiStrawApiCall {
            crowdFundingAPI.fundingCreate(fundingCreateRequest = fundingCreateRequest)
                .errorHandling()
        }

    override suspend fun fundingPopularList(): List<FundingResponse> = indiStrawApiCall {
        crowdFundingAPI.fundingPopularList()
    }

    override suspend fun fundingDetail(fundingIdx: Long): FundingDetailResponse =
        indiStrawApiCall {
            crowdFundingAPI.fundingDetail(fundingIdx = fundingIdx)
        }

    override suspend fun fundingAll(): Flow<PagingData<FundingResponse>> =
        Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                FundingPagingSource(
                    crowdFundingAPI = crowdFundingAPI
                )
            }).flow

    override suspend fun fundingMy(): List<FundingResponse> = indiStrawApiCall {
        crowdFundingAPI.fundingMy()
    }

    override suspend fun fundingMyDetail(fundingIdx: Long): MyFundingResponse =
        indiStrawApiCall {
            crowdFundingAPI.fundingMyDetail(fundingIdx = fundingIdx)
        }
}