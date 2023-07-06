package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.FundingAPI
import com.danbam.data.remote.pagingsource.FundingPagingSource
import com.danbam.data.remote.request.FundingCreateRequest
import com.danbam.data.remote.response.FundingDetailResponse
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FundingRemoteDataSourceImpl @Inject constructor(
    private val fundingAPI: FundingAPI
) : FundingRemoteDataSource {
    override suspend fun fundingCreate(fundingCreateRequest: FundingCreateRequest) =
        indiStrawApiCall {
            fundingAPI.fundingCreate(fundingCreateRequest = fundingCreateRequest).errorHandling()
        }

    override suspend fun fundingPopularList(): List<FundingResponse> = indiStrawApiCall {
        fundingAPI.fundingPopularList()
    }

    override suspend fun fundingDetail(fundingIndex: Long): FundingDetailResponse =
        indiStrawApiCall {
            fundingAPI.fundingDetail(fundingIndex = fundingIndex)
        }

    override suspend fun fundingAll(): Flow<PagingData<FundingResponse>> =
        Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                FundingPagingSource(
                    fundingAPI = fundingAPI
                )
            }).flow
}