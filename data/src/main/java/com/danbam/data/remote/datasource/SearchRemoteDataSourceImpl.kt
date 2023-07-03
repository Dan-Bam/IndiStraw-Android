package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.SearchAPI
import com.danbam.data.remote.response.RelatedSearchResponse
import com.danbam.data.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchAPI: SearchAPI
) : SearchRemoteDataSource {
    override suspend fun getRelatedSearch(keyword: String): List<RelatedSearchResponse> =
        indiStrawApiCall {
            searchAPI.gerRelatedSearch(keyword)
        }
}