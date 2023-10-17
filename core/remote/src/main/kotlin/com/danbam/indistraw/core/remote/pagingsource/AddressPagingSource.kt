package com.danbam.indistraw.core.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danbam.indistraw.core.remote.api.AddressAPI
import com.danbam.indistraw.core.remote.response.auth.AddressResponse
import javax.inject.Inject

class AddressPagingSource(
    private val addressAPI: AddressAPI,
    private val keyword: String,
) : PagingSource<Int, AddressResponse.Results.Juso>() {
    override fun getRefreshKey(state: PagingState<Int, AddressResponse.Results.Juso>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AddressResponse.Results.Juso> {
        return try {
            val page = params.key ?: 1
            val response = addressAPI.getAddress(currentPage = page, keyword = keyword)

            LoadResult.Page(
                data = response.results.juso,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.juso.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}