package com.danbam.data.local.datasource

import com.danbam.data.local.preference.AuthPreference
import java.time.LocalDateTime
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
) : AuthLocalDataSource {
    override suspend fun saveAccessToken(accessToken: String) =
        authPreference.saveAccessToken(accessToken = accessToken)

    override suspend fun fetchAccessToken(): String =
        authPreference.fetchAccessToken()

    override suspend fun clearAccessToken() =
        authPreference.clearAccessToken()

    override suspend fun saveRefreshToken(refreshToken: String) =
        authPreference.saveRefreshToken(refreshToken = refreshToken)

    override suspend fun fetchRefreshToken(): String =
        authPreference.fetchRefreshToken()

    override suspend fun clearRefreshToken() =
        authPreference.clearRefreshToken()

    override suspend fun saveAccessExpiredAt(accessExpiredAt: String) =
        authPreference.saveAccessExpiredAt(accessExpiredAt = accessExpiredAt)

    override suspend fun fetchAccessExpiredAt(): LocalDateTime =
        LocalDateTime.parse(authPreference.fetchAccessExpiredAt())

    override suspend fun clearAccessExpiredAt() =
        authPreference.clearAccessExpiredAt()

    override suspend fun saveRefreshExpiredAt(refreshExpiredAt: String) =
        authPreference.saveRefreshExpiredAt(refreshExpiredAt = refreshExpiredAt)

    override suspend fun fetchRefreshExpiredAt(): LocalDateTime =
        LocalDateTime.parse(authPreference.fetchRefreshExpiredAt())

    override suspend fun clearRefreshExpiredAt() =
        authPreference.clearRefreshExpiredAt()
}