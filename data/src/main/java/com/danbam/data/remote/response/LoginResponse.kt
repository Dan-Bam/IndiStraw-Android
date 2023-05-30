package com.danbam.data.remote.response

import com.danbam.domain.entity.LoginEntity
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("accessExpiredAt")
    val accessExpiredAt: LocalDateTime,
    @SerializedName("refreshExpiredAt")
    val refreshExpiredAt: LocalDateTime,
) {
    fun toEntity() = LoginEntity(
        accessToken = accessToken,
        refreshToken = refreshToken,
        accessExpiredAt = accessExpiredAt,
        refreshExpiredAt = refreshExpiredAt
    )
}
