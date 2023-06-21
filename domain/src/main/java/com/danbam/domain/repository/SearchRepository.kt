package com.danbam.domain.repository

import com.danbam.domain.entity.RecentSearchEntity

interface SearchRepository {
    suspend fun search(recentSearchEntity: RecentSearchEntity)
    suspend fun getRecentSearch(): List<RecentSearchEntity>
}