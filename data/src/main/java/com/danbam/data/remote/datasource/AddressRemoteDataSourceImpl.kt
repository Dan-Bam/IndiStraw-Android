package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.AddressAPI
import com.danbam.data.remote.pagingsource.AddressPagingSource
import com.danbam.data.remote.response.auth.AddressResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddressRemoteDataSourceImpl @Inject constructor(
    private val addressAPI: AddressAPI,
) : AddressRemoteDataSource {
    override suspend fun getAddress(keyword: String): Flow<PagingData<AddressResponse.Results.Juso>> =
        Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                AddressPagingSource(
                    addressAPI = addressAPI,
                    keyword = keyword
                )
            }).flow
}