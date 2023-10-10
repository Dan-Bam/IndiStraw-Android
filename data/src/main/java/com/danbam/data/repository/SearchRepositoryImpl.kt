package com.danbam.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.data.local.datasource.SearchLocalDataSource
import com.danbam.data.local.entity.toDB
import com.danbam.data.local.entity.toDomain
import com.danbam.data.remote.datasource.SearchRemoteDataSource
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.entity.MovieEntity
import com.danbam.domain.entity.RecentSearchEntity
import com.danbam.domain.entity.RelatedSearchEntity
import com.danbam.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun getRelatedSearch(keyword: String): List<RelatedSearchEntity> =
        searchRemoteDataSource.getRelatedSearch(keyword = keyword).map { it.toEntity() }

    override suspend fun searchMovie(recentSearchEntity: RecentSearchEntity): Flow<PagingData<MovieEntity>> {
        saveRecentSearch(recentSearchEntity = recentSearchEntity)
        return searchRemoteDataSource.searchMovie(keyword = recentSearchEntity.search)
            .map { it.map { it.toEntity() } }
    }

    override suspend fun searchFunding(recentSearchEntity: RecentSearchEntity): Flow<PagingData<FundingEntity>> {
        saveRecentSearch(recentSearchEntity = recentSearchEntity)
        return searchRemoteDataSource.searchFunding(keyword = recentSearchEntity.search)
            .map { it.map { it.toEntity() } }
    }

    override suspend fun getRecentSearch(): List<RecentSearchEntity> =
        searchLocalDataSource.getRecentSearch().map { it.toDomain() }

    override suspend fun popularTag(): List<String> = searchRemoteDataSource.popularTag().tagList

    private suspend fun saveRecentSearch(recentSearchEntity: RecentSearchEntity) {
        searchLocalDataSource.search(recentSearchEntity = recentSearchEntity.toDB())
        val searchRecentList = searchLocalDataSource.getRecentSearch()
        if (searchRecentList.size > 10) {
            searchLocalDataSource.deleteRecentSearch(
                searchRecentList.slice(10 until searchRecentList.size).toList()
            )
        }
    }
}