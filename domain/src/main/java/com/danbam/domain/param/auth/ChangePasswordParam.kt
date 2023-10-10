package com.danbam.domain.param.auth

data class ChangePasswordParam(
    val phoneNumber: String,
    val password: String,
)