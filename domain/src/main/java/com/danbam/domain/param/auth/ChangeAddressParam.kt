package com.danbam.domain.param.auth

data class ChangeAddressParam(
    val zipCode: String,
    val streetAddress: String,
    val detailAddress: String,
)