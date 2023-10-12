package com.danbam.indistraw.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.indistraw.core.data.remote.datasource.AddressRemoteDataSource
import com.danbam.indistraw.core.data.remote.response.auth.toEntity
import com.danbam.indistraw.core.domain.entity.auth.AddressEntity
import com.danbam.indistraw.core.domain.repository.AddressRepository
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