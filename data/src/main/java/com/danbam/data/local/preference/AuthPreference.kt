package com.danbam.data.local.preference

interface AuthPreference {
    suspend fun saveAccessToken(accessToken: String)

    suspend fun fetchAccessToken(): String?

    suspend fun clearAccessToken()

    suspend fun saveRefreshToken(refreshToken: String)

    suspend fun fetchRefreshToken(): String?

    suspend fun clearRefreshToken()

    suspend fun saveAccessExpiredAt(accessExpiredAt: String)

    suspend fun fetchAccessExpiredAt(): String?

    suspend fun clearAccessExpiredAt()

    suspend fun saveRefreshExpiredAt(refreshExpiredAt: String)

    suspend fun fetchRefreshExpiredAt(): String?

    suspend fun clearRefreshExpiredAt()
}