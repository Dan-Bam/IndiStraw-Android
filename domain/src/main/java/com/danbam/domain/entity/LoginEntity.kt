package com.danbam.domain.entity

import java.time.LocalDateTime

data class LoginEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: LocalDateTime,
    val refreshExpiredAt: LocalDateTime,
)