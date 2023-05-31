package com.danbam.data.local.datasource

import java.time.LocalDateTime

interface AuthLocalDataSource {
    suspend fun saveAccessToken(accessToken: String)

    suspend fun fetchAccessToken(): String

    suspend fun clearAccessToken()

    suspend fun saveRefreshToken(refreshToken: String)

    suspend fun fetchRefreshToken(): String

    suspend fun clearRefreshToken()

    suspend fun saveAccessExpiredAt(accessExpiredAt: String)

    suspend fun fetchAccessExpiredAt(): LocalDateTime

    suspend fun clearAccessExpiredAt()

    suspend fun saveRefreshExpiredAt(refreshExpiredAt: String)

    suspend fun fetchRefreshExpiredAt(): LocalDateTime

    suspend fun clearRefreshExpiredAt()
}