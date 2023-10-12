package com.danbam.indistraw.domain.repository

import androidx.paging.PagingData
import com.danbam.indistraw.domain.entity.funding.FundingEntity
import com.danbam.indistraw.domain.entity.movie.MovieEntity
import com.danbam.indistraw.domain.entity.search.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getRelatedSearch(keyword: String): List<String>
    suspend fun searchMovie(recentSearchEntity: RecentSearchEntity): Flow<PagingData<MovieEntity>>
    suspend fun searchFunding(recentSearchEntity: RecentSearchEntity): Flow<PagingData<FundingEntity>>
    suspend fun getRecentSearch(): List<RecentSearchEntity>
    suspend fun popularTag(): List<String>
}