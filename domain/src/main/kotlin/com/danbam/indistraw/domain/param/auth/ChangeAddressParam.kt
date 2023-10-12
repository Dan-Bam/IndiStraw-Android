package com.danbam.indistraw.domain.param.auth

data class ChangeAddressParam(
    val zipCode: String,
    val streetAddress: String,
    val detailAddress: String,
)