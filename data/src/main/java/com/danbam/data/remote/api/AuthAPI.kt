package com.danbam.data.remote.api

import com.danbam.data.remote.request.LoginRequest
import com.danbam.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthAPI {
    @POST("auth/signin")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

    @PATCH("auth/reissue")
    suspend fun refresh(
        @Header("refreshToken") refreshToken: String,
    ): LoginResponse

    @POST("auth/send/phone-number/{phoneNumber}")
    suspend fun sendCertificateNumber(
        @Path("phoneNumber") phoneNumber: String,
    )

    @HEAD("auth/auth-code/{authCode}/phone-number/{phoneNumber}")
    suspend fun checkCertificateNumber(
        @Path("authCode") authCode: Int,
        @Path("phoneNumber") phoneNumber: String,
    )
}