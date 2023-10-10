package com.danbam.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.data.remote.datasource.AddressRemoteDataSource
import com.danbam.data.remote.response.auth.toEntity
import com.danbam.domain.entity.auth.AddressEntity
import com.danbam.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressRemoteDataSource: AddressRemoteDataSource,
) : AddressRepository {
    override suspend fun getAddress(keyword: String): Flow<PagingData<AddressEntity>> =
        addressRemoteDataSource.getAddress(keyword = keyword).map {
            it.map { it.toEntity() }
        }
}