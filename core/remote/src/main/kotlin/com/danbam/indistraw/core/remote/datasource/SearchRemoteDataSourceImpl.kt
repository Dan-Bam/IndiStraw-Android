package com.danbam.indistraw.core.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.indistraw.core.remote.api.SearchAPI
import com.danbam.indistraw.core.remote.pagingsource.SearchFundingPagingSource
import com.danbam.indistraw.core.remote.pagingsource.SearchMoviePagingSource
import com.danbam.indistraw.core.remote.response.funding.FundingResponse
import com.danbam.indistraw.core.remote.response.movie.MovieResponse
import com.danbam.indistraw.core.remote.response.search.PopularTagResponse
import com.danbam.indistraw.core.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchAPI: SearchAPI
) : SearchRemoteDataSource {
    override suspend fun getRelatedSearch(keyword: String): List<String> =
        indiStrawApiCall {
            searchAPI.gerRelatedSearch(keyword)
        }

    override suspend fun searchMovie(keyword: String): Flow<PagingData<MovieResponse>> =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchMoviePagingSource(
                    searchAPI = searchAPI,
                    keyword = keyword
                )
            }).flow

    override suspend fun searchFunding(keyword: String): Flow<PagingData<FundingResponse>> =
        Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchFundingPagingSource(
                    searchAPI = searchAPI,
                    keyword = keyword
                )
            }).flow

    override suspend fun popularTag(): PopularTagResponse =
        indiStrawApiCall {
            searchAPI.popularTag()
        }
}