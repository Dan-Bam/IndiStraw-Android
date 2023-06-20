package com.danbam.data.local.datasource

import com.danbam.data.local.dao.RecentSearchDao
import com.danbam.data.local.entity.RecentSearchEntity
import javax.inject.Inject

class SearchLocalDataSourceImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao,
) : SearchLocalDataSource {
    override suspend fun search(recentSearchEntity: RecentSearchEntity) =
        recentSearchDao.search(recentSearchEntity = recentSearchEntity)

    override suspend fun getRecentSearch(): List<RecentSearchEntity?> =
        recentSearchDao.getRecentSearch()
}