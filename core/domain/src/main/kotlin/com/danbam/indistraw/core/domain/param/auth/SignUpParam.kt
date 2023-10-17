package com.danbam.indistraw.core.domain.param.auth

data class SignUpParam(
    val id: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val profileUrl: String?,
)