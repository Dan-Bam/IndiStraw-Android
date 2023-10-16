package com.danbam.indistraw.core.domain.param.auth

data class ChangePasswordParam(
    val phoneNumber: String,
    val password: String,
)