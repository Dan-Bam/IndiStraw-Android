package com.danbam.indistraw.core.local.datasource

import com.danbam.indistraw.core.local.dao.RecentSearchDao
import com.danbam.indistraw.core.local.search.RecentSearchEntity
import javax.inject.Inject

class SearchLocalDataSourceImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao,
) : SearchLocalDataSource {
    override suspend fun search(recentSearchEntity: RecentSearchEntity) =
        recentSearchDao.search(recentSearchEntity = recentSearchEntity)

    override suspend fun getRecentSearch(): List<RecentSearchEntity> =
        recentSearchDao.getRecentSearch().reversed()

    override suspend fun deleteRecentSearch(elements: List<RecentSearchEntity>) =
        recentSearchDao.deleteRecentSearch(elements = elements)
}