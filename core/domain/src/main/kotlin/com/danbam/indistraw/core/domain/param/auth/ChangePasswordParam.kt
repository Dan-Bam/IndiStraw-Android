package com.danbam.indistraw.core.param.auth

data class ChangePasswordParam(
    val phoneNumber: String,
    val password: String,
)