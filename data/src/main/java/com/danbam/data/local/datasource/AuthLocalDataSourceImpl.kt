package com.danbam.data.local.datasource

import com.danbam.data.local.preference.AuthPreference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
) : AuthLocalDataSource {
    override fun saveAccessToken(accessToken: String) =
        authPreference.saveAccessToken(accessToken = accessToken)

    override fun fetchAccessToken(): String? =
        authPreference.fetchAccessToken()

    override fun clearAccessToken() =
        authPreference.clearAccessToken()

    override fun saveRefreshToken(refreshToken: String) =
        authPreference.saveRefreshToken(refreshToken = refreshToken)

    override fun fetchRefreshToken(): String? =
        authPreference.fetchRefreshToken()

    override fun clearRefreshToken() =
        authPreference.clearRefreshToken()

    override fun saveAccessExpiredAt(accessExpiredAt: String) =
        authPreference.saveAccessExpiredAt(accessExpiredAt = accessExpiredAt)

    override fun fetchAccessExpiredAt(): LocalDateTime? =
        authPreference.fetchAccessExpiredAt()
            ?.let { LocalDateTime.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")) }

    override fun clearAccessExpiredAt() =
        authPreference.clearAccessExpiredAt()

    override fun saveRefreshExpiredAt(refreshExpiredAt: String) =
        authPreference.saveRefreshExpiredAt(refreshExpiredAt = refreshExpiredAt)

    override fun fetchRefreshExpiredAt(): LocalDateTime? =
        authPreference.fetchRefreshExpiredAt()
            ?.let { LocalDateTime.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")) }

    override fun clearRefreshExpiredAt() =
        authPreference.clearRefreshExpiredAt()
}