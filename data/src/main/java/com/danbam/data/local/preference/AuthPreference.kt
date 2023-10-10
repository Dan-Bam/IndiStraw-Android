package com.danbam.data.local.preference

interface AuthPreference {
    fun saveAccessToken(accessToken: String)

    fun fetchAccessToken(): String?

    fun clearAccessToken()

    fun saveRefreshToken(refreshToken: String)

    fun fetchRefreshToken(): String?

    fun clearRefreshToken()

    fun saveAccessExpiredAt(accessExpiredAt: String)

    fun fetchAccessExpiredAt(): String?

    fun clearAccessExpiredAt()

    fun saveRefreshExpiredAt(refreshExpiredAt: String)

    fun fetchRefreshExpiredAt(): String?

    fun clearRefreshExpiredAt()
}