package com.danbam.indistraw.core.remote.response.auth

import com.danbam.indistraw.core.domain.entity.auth.LoginEntity
import com.google.gson.annotations.SerializedName

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
