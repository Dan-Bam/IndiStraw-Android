package com.danbam.domain.param

data class ChangePasswordParam(
    val phoneNumber: String,
    val password: String,
)