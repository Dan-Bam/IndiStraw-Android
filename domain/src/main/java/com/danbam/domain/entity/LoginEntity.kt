package com.danbam.domain.entity

data class LoginEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
)