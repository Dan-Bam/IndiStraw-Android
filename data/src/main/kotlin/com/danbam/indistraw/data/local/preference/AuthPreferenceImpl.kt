package com.danbam.indistraw.data.local.preference

import android.content.SharedPreferences
import com.danbam.indistraw.data.local.util.clearString
import com.danbam.indistraw.data.local.util.fetchString
import com.danbam.indistraw.data.local.util.saveString
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

    override fun saveAccessToken(accessToken: String) =
        sharedPreferences.saveString(ACCESS_TOKEN, accessToken)

    override fun fetchAccessToken(): String? =
        sharedPreferences.fetchString(ACCESS_TOKEN)

    override fun clearAccessToken() =
        sharedPreferences.clearString(ACCESS_TOKEN)

    override fun saveRefreshToken(refreshToken: String) =
        sharedPreferences.saveString(REFRESH_TOKEN, refreshToken)

    override fun fetchRefreshToken(): String? =
        sharedPreferences.fetchString(REFRESH_TOKEN)

    override fun clearRefreshToken() =
        sharedPreferences.clearString(REFRESH_TOKEN)

    override fun saveAccessExpiredAt(accessExpiredAt: String) =
        sharedPreferences.saveString(ACCESS_EXPIRED_AT, accessExpiredAt)

    override fun fetchAccessExpiredAt(): String? =
        sharedPreferences.fetchString(ACCESS_EXPIRED_AT)

    override fun clearAccessExpiredAt() =
        sharedPreferences.clearString(ACCESS_EXPIRED_AT)

    override fun saveRefreshExpiredAt(refreshExpiredAt: String) =
        sharedPreferences.saveString(REFRESH_EXPIRED_AT, refreshExpiredAt)

    override fun fetchRefreshExpiredAt(): String? =
        sharedPreferences.fetchString(REFRESH_EXPIRED_AT)

    override fun clearRefreshExpiredAt() =
        sharedPreferences.clearString(REFRESH_EXPIRED_AT)
}