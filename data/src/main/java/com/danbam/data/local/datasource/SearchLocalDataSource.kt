package com.danbam.data.local.datasource

import com.danbam.data.local.entity.RecentSearchEntity

interface SearchLocalDataSource {
    suspend fun search(recentSearchEntity: RecentSearchEntity)
    suspend fun getRecentSearch(): List<RecentSearchEntity?>
}