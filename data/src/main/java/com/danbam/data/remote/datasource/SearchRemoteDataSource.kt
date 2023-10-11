package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.response.funding.FundingResponse
import com.danbam.data.remote.response.movie.MovieResponse
import com.danbam.data.remote.response.search.PopularTagResponse
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    suspend fun getRelatedSearch(keyword: String): List<String>
    suspend fun searchMovie(keyword: String): Flow<PagingData<MovieResponse>>
    suspend fun searchFunding(keyword: String): Flow<PagingData<FundingResponse>>
    suspend fun popularTag(): PopularTagResponse
}