package com.danbam.indistraw.feature.mobile.profile.detail_address

sealed class DetailAddressSideEffect {
    object EmptyDetailAddressException : DetailAddressSideEffect()
    object SuccessChangeAddress : DetailAddressSideEffect()
}