package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.response.RelatedSearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    suspend fun getRelatedSearch(keyword: String): Flow<PagingData<RelatedSearchResponse.RelatedSearchItem>>
}