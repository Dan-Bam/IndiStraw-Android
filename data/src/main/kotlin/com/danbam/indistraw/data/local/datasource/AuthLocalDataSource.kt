package com.danbam.indistraw.data.local.datasource

import java.time.LocalDateTime

interface AuthLocalDataSource {
    fun saveAccessToken(accessToken: String)

    fun fetchAccessToken(): String?

    fun clearAccessToken()

    fun saveRefreshToken(refreshToken: String)

    fun fetchRefreshToken(): String?

    fun clearRefreshToken()

    fun saveAccessExpiredAt(accessExpiredAt: String)

    fun fetchAccessExpiredAt(): LocalDateTime?

    fun clearAccessExpiredAt()

    fun saveRefreshExpiredAt(refreshExpiredAt: String)

    fun fetchRefreshExpiredAt(): LocalDateTime?

    fun clearRefreshExpiredAt()
}