package com.danbam.indistraw.core.domain.param.auth

data class ChangeAddressParam(
    val zipCode: String,
    val streetAddress: String,
    val detailAddress: String,
)