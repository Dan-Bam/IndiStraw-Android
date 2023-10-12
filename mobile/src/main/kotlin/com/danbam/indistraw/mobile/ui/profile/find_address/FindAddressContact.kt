package com.danbam.indistraw.mobile.ui.profile.find_address

import androidx.paging.PagingData
import com.danbam.indistraw.domain.entity.auth.AddressEntity
import kotlinx.coroutines.flow.Flow

data class FindAddressState(
    val findAddressPager: Flow<PagingData<AddressEntity>>? = null,
)

sealed class FindAddressSideEffect {
}