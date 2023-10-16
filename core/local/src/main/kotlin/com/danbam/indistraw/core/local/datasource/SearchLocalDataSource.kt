package com.danbam.indistraw.core.local.datasource

import com.danbam.indistraw.core.local.search.RecentSearchEntity


interface SearchLocalDataSource {
    suspend fun search(recentSearchEntity: RecentSearchEntity)
    suspend fun getRecentSearch(): List<RecentSearchEntity>
    suspend fun deleteRecentSearch(elements: List<RecentSearchEntity>)
}