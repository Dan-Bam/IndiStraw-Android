package com.danbam.data.local.preference

import android.content.SharedPreferences
import com.danbam.data.local.util.clearString
import com.danbam.data.local.util.fetchString
import com.danbam.data.local.util.saveString
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AuthPreference {

    companion object AuthKey {
        const val ACCESS_TOKEN = "accessToken"
        const val REFRESH_TOKEN = "refreshToken"
        const val ACCESS_EXPIRED_AT = "accessExpiredAt"
        const val REFRESH_EXPIRED_AT = "refreshExpired_at"
    }

    override suspend fun saveAccessToken(accessToken: String) =
        sharedPreferences.saveString(ACCESS_TOKEN, accessToken)

    override suspend fun fetchAccessToken(): String =
        sharedPreferences.fetchString(ACCESS_TOKEN)

    override suspend fun clearAccessToken() =
        sharedPreferences.clearString(ACCESS_TOKEN)

    override suspend fun saveRefreshToken(refreshToken: String) =
        sharedPreferences.saveString(REFRESH_TOKEN, refreshToken)

    override suspend fun fetchRefreshToken(): String =
        sharedPreferences.fetchString(REFRESH_TOKEN)

    override suspend fun clearRefreshToken() =
        sharedPreferences.clearString(REFRESH_TOKEN)

    override suspend fun saveAccessExpiredAt(accessExpiredAt: String) =
        sharedPreferences.saveString(ACCESS_EXPIRED_AT, accessExpiredAt)

    override suspend fun fetchAccessExpiredAt(): String =
        sharedPreferences.fetchString(ACCESS_EXPIRED_AT)

    override suspend fun clearAccessExpiredAt() =
        sharedPreferences.clearString(ACCESS_EXPIRED_AT)

    override suspend fun saveRefreshExpiredAt(refreshExpiredAt: String) =
        sharedPreferences.saveString(REFRESH_EXPIRED_AT, refreshExpiredAt)

    override suspend fun fetchRefreshExpiredAt(): String =
        sharedPreferences.fetchString(REFRESH_EXPIRED_AT)

    override suspend fun clearRefreshExpiredAt() =
        sharedPreferences.clearString(REFRESH_EXPIRED_AT)
}