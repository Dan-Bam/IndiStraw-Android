package com.danbam.mobile.ui.profile.find_address

import androidx.paging.PagingData
import com.danbam.domain.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

data class FindAddressState(
    val findAddressPager: Flow<PagingData<AddressEntity>>? = null,
)

sealed class FindAddressSideEffect {
}