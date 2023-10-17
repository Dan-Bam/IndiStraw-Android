package com.danbam.indistraw.core.remote.api

import com.danbam.indistraw.core.remote.request.auth.LoginRequest
import com.danbam.indistraw.core.remote.request.auth.SignUpRequest
import com.danbam.indistraw.core.remote.response.auth.LoginResponse
import com.danbam.indistraw.core.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthAPI {
    @POST("${EndPoint.AUTH}/signup")
    suspend fun signup(
        @Body signUpRequest: SignUpRequest,
    )

    @POST("${EndPoint.AUTH}/signin")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

    @PATCH("${EndPoint.AUTH}/reissue")
    suspend fun refresh(
        @Header("refreshToken") refreshToken: String,
    ): LoginResponse

    @HEAD("${EndPoint.AUTH}/check/phone-number/{phoneNumber}/type/{type}")
    suspend fun checkPhoneNumber(
        @Path("phoneNumber") phoneNumber: String,
        @Path("type") type: String,
    ): Response<Void?>

    @HEAD("${EndPoint.AUTH}/check/id/{id}")
    suspend fun checkId(
        @Path("id") id: String,
    ): Response<Void?>

    @POST("${EndPoint.AUTH}/send/phone-number/{phoneNumber}")
    suspend fun sendCertificateNumber(
        @Path("phoneNumber") phoneNumber: String,
    ): Response<Void?>

    @GET("${EndPoint.AUTH}/auth-code/{authCode}/phone-number/{phoneNumber}")
    suspend fun checkCertificateNumber(
        @Path("authCode") authCode: Int,
        @Path("phoneNumber") phoneNumber: String,
    ): Response<Void?>

    @DELETE("${EndPoint.AUTH}/logout")
    suspend fun logout(
        @Header("refreshToken") refreshToken: String,
    ): Response<Void?>
}