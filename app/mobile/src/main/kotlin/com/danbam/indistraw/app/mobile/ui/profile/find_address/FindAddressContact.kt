package com.danbam.indistraw.app.mobile.ui.profile.find_address

import androidx.paging.PagingData
import com.danbam.indistraw.core.domain.entity.auth.AddressEntity
import kotlinx.coroutines.flow.Flow

data class FindAddressState(
    val findAddressPager: Flow<PagingData<AddressEntity>>? = null,
)

sealed class FindAddressSideEffect {
}