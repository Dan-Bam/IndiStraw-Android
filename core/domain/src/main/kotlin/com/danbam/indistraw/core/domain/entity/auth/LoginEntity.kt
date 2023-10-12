package com.danbam.indistraw.core.domain.entity.auth

data class LoginEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
)