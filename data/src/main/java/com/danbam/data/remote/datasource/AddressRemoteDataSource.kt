package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.response.auth.AddressResponse
import kotlinx.coroutines.flow.Flow

interface AddressRemoteDataSource {
    suspend fun getAddress(keyword: String): Flow<PagingData<AddressResponse.Results.Juso>>
}