package com.danbam.indistraw.core.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danbam.indistraw.core.data.remote.api.SearchAPI
import com.danbam.indistraw.core.data.remote.response.funding.FundingResponse
import javax.inject.Inject

class SearchFundingPagingSource(
    @Inject private val searchAPI: SearchAPI,
    private val keyword: String,
) : PagingSource<Int, FundingResponse>() {
    override fun getRefreshKey(state: PagingState<Int, FundingResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FundingResponse> {
        return try {
            val page = params.key ?: 0
            val response = searchAPI.searchFunding(page = page, keyword = keyword)

            LoadResult.Page(
                data = response.list,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isLast) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}