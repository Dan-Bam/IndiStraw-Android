package com.danbam.data.repository

import com.danbam.data.local.datasource.SearchLocalDataSource
import com.danbam.data.local.entity.toDB
import com.danbam.data.local.entity.toDomain
import com.danbam.domain.entity.RecentSearchEntity
import com.danbam.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
) : SearchRepository {
    override suspend fun search(recentSearchEntity: RecentSearchEntity) =
        searchLocalDataSource.search(recentSearchEntity = recentSearchEntity.toDB())

    override suspend fun getRecentSearch(): List<RecentSearchEntity?> =
        searchLocalDataSource.getRecentSearch().map { it?.toDomain() }
}