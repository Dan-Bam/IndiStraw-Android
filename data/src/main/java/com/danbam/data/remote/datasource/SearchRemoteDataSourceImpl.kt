package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.SearchAPI
import com.danbam.data.remote.pagingsource.RelatedSearchPagingSource
import com.danbam.data.remote.response.RelatedSearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchAPI: SearchAPI
) : SearchRemoteDataSource {
    override suspend fun getRelatedSearch(keyword: String): Flow<PagingData<RelatedSearchResponse.RelatedSearchItem>> =
        Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                RelatedSearchPagingSource(
                    searchAPI = searchAPI,
                    keyword = keyword
                )
            }).flow
}