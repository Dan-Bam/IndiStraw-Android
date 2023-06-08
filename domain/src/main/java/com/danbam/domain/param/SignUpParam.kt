package com.danbam.domain.param

data class SignUpParam(
    val id: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val profileUrl: String,
)