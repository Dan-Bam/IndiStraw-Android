package com.danbam.data.remote.interceptor

import com.danbam.data.BuildConfig
import com.danbam.data.local.datasource.AuthLocalDataSource
import com.danbam.data.remote.response.LoginResponse
import com.danbam.data.util.default
import com.danbam.domain.exception.ExpiredTokenException
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.time.LocalDateTime
import javax.inject.Inject

class IndiStrawInterceptor @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val method = request.method
        val path = request.url.encodedPath
        val ignorePath = BuildConfig.IGNORE_PATH.split(", ")
        ignorePath.forEach {
            if (path.startsWith(ignorePath[1]) && method == "PATCH") return@forEach
            if (path.startsWith(it)) return chain.proceed(request)
        }
        val now = LocalDateTime.now().default()
        val accessExpiredAt =
            authLocalDataSource.fetchAccessExpiredAt() ?: throw ExpiredTokenException()
        val refreshExpiredAt =
            authLocalDataSource.fetchRefreshExpiredAt() ?: throw ExpiredTokenException()
        if (now.isAfter(refreshExpiredAt)) {
            with(authLocalDataSource) {
                clearAccessToken()
                clearRefreshToken()
                clearAccessExpiredAt()
                clearRefreshExpiredAt()
            }
            throw ExpiredTokenException()
        } else if (now.isAfter(accessExpiredAt)) {
            val client = OkHttpClient()
            val refreshRequest = Request.Builder()
                .url("${BuildConfig.BASE_URL}auth/reissue")
                .patch("".toRequestBody("application/json".toMediaTypeOrNull()))
                .addHeader("refreshToken", "Bearer ${authLocalDataSource.fetchRefreshToken()}")
                .build()
            val response = client.newCall(refreshRequest).execute()
            if (response.isSuccessful) {
                val loginResponse = Gson().fromJson(
                    response.body!!.string(),
                    LoginResponse::class.java
                )
                authLocalDataSource.saveAccessToken(loginResponse.accessToken)
                authLocalDataSource.saveRefreshToken(loginResponse.refreshToken)
                authLocalDataSource.saveAccessExpiredAt(loginResponse.accessExpiredAt)
                authLocalDataSource.saveRefreshExpiredAt(loginResponse.refreshExpiredAt)
            } else throw ExpiredTokenException()
        }
        return chain.proceed(
            request = request.newBuilder().addHeader(
                "Authorization",
                "Bearer ${authLocalDataSource.fetchAccessToken()}"
            ).build()
        )
    }
}