package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.LoginRequest
import com.danbam.data.remote.response.LoginResponse

interface AuthRemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun refresh(refreshToken: String): LoginResponse
    suspend fun sendCertificateNumber(phoneNumber: String)
    suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String)
}