package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.response.MoviePageResponse
import com.danbam.data.remote.response.MovieResponse
import com.danbam.data.remote.response.PopularTagResponse
import com.danbam.data.remote.response.RelatedSearchResponse
import com.danbam.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    suspend fun getRelatedSearch(keyword: String): List<RelatedSearchResponse>
    suspend fun searchMovie(keyword: String): Flow<PagingData<MovieResponse>>
    suspend fun searchFunding(keyword: String): Flow<PagingData<FundingResponse>>
    suspend fun popularTag(): PopularTagResponse
}