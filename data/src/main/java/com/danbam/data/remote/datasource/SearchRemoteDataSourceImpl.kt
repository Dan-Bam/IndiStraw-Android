package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.SearchAPI
import com.danbam.data.remote.pagingsource.SearchFundingPagingSource
import com.danbam.data.remote.pagingsource.SearchMoviePagingSource
import com.danbam.data.remote.response.FundingResponse
import com.danbam.data.remote.response.MoviePageResponse
import com.danbam.data.remote.response.MovieResponse
import com.danbam.data.remote.response.PopularTagResponse
import com.danbam.data.remote.response.RelatedSearchResponse
import com.danbam.data.remote.util.indiStrawApiCall
import com.danbam.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchAPI: SearchAPI
) : SearchRemoteDataSource {
    override suspend fun getRelatedSearch(keyword: String): List<RelatedSearchResponse> =
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

    override suspend fun popularTag(): PopularTagResponse = indiStrawApiCall {
        searchAPI.popularTag()
    }
}