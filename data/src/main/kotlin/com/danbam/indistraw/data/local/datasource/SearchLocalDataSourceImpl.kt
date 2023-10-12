package com.danbam.indistraw.data.local.datasource

import com.danbam.indistraw.data.local.dao.RecentSearchDao
import com.danbam.indistraw.data.local.entity.search.RecentSearchEntity
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