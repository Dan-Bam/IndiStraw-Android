package com.danbam.data.local.datasource

import com.danbam.data.local.entity.search.RecentSearchEntity

interface SearchLocalDataSource {
    suspend fun search(recentSearchEntity: RecentSearchEntity)
    suspend fun getRecentSearch(): List<RecentSearchEntity>
    suspend fun deleteRecentSearch(elements: List<RecentSearchEntity>)
}