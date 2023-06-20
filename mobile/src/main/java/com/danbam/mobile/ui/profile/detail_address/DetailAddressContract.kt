package com.danbam.mobile.ui.profile.detail_address

sealed class DetailAddressSideEffect {
    object EmptyDetailAddressException : DetailAddressSideEffect()
    object SuccessChangeAddress : DetailAddressSideEffect()
}