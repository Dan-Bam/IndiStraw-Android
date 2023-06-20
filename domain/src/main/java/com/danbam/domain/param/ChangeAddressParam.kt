package com.danbam.domain.param

data class ChangeAddressParam(
    val zipCode: String,
    val streetAddress: String,
    val detailAddress: String,
)