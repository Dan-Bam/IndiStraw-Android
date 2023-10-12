package com.danbam.indistraw.domain.param.auth

data class ChangePasswordParam(
    val phoneNumber: String,
    val password: String,
)