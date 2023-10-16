package com.danbam.indistraw.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.indistraw.core.local.datasource.SearchLocalDataSource
import com.danbam.indistraw.core.local.search.toDB
import com.danbam.indistraw.core.local.search.toDomain
import com.danbam.indistraw.core.data.remote.datasource.SearchRemoteDataSource
import com.danbam.indistraw.core.data.remote.response.funding.toEntity
import com.danbam.indistraw.core.data.remote.response.movie.toEntity
import com.danbam.indistraw.core.entity.funding.FundingEntity
import com.danbam.indistraw.core.entity.movie.MovieEntity
import com.danbam.indistraw.core.entity.search.RecentSearchEntity
import com.danbam.indistraw.core.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: com.danbam.indistraw.core.local.datasource.SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun getRelatedSearch(keyword: String): List<String> =
        searchRemoteDataSource.getRelatedSearch(keyword = keyword)

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