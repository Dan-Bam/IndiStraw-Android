package com.danbam.indistraw.core.data.remote.datasource

import com.danbam.indistraw.core.data.remote.request.auth.LoginRequest
import com.danbam.indistraw.core.data.remote.request.auth.SignUpRequest
import com.danbam.indistraw.core.data.remote.response.auth.LoginResponse

interface AuthRemoteDataSource {
    suspend fun signup(signUpRequest: SignUpRequest)
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun refresh(refreshToken: String): LoginResponse
    suspend fun checkPhoneNumber(phoneNumber: String, type: String)
    suspend fun checkId(id: String)
    suspend fun sendCertificateNumber(phoneNumber: String)
    suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String)
    suspend fun logout(refreshToken: String)
}