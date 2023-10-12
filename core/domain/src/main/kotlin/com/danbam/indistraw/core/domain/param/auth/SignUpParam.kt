package com.danbam.indistraw.core.param.auth

data class SignUpParam(
    val id: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val profileUrl: String?,
)