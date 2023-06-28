package com.danbam.domain.repository

import androidx.paging.PagingData
import com.danbam.domain.entity.RecentSearchEntity
import com.danbam.domain.entity.RelatedSearchEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getRelatedSearch(keyword: String): Flow<PagingData<RelatedSearchEntity>>
    suspend fun search(recentSearchEntity: RecentSearchEntity)
    suspend fun getRecentSearch(): List<RecentSearchEntity>
}