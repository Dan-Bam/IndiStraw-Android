package com.danbam.data.remote.response

import com.danbam.domain.entity.LoginEntity
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("accessTokenExpiredAt")
    val accessExpiredAt: String,
    @SerializedName("refreshTokenExpiredAt")
    val refreshExpiredAt: String,
)

fun LoginResponse.toEntity() = LoginEntity(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessExpiredAt = accessExpiredAt,
    refreshExpiredAt = refreshExpiredAt
)
