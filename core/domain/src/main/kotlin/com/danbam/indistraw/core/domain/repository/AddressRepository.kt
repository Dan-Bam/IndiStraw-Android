package com.danbam.indistraw.core.domain.repository

import androidx.paging.PagingData
import com.danbam.indistraw.core.domain.entity.auth.AddressEntity
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun getAddress(keyword: String): Flow<PagingData<AddressEntity>>
}