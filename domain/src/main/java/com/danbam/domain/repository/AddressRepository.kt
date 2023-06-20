package com.danbam.domain.repository

import androidx.paging.PagingData
import com.danbam.domain.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun getAddress(keyword: String): Flow<PagingData<AddressEntity>>
}