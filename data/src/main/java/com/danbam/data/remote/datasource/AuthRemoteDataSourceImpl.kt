package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.AuthAPI
import com.danbam.data.remote.request.LoginRequest
import com.danbam.data.remote.request.SignUpRequest
import com.danbam.data.remote.response.LoginResponse
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI,
) : AuthRemoteDataSource {
    override suspend fun signup(signUpRequest: SignUpRequest) = indiStrawApiCall {
        authAPI.signup(signUpRequest = signUpRequest)
    }

    override suspend fun login(loginRequest: LoginRequest): LoginResponse = indiStrawApiCall {
        authAPI.login(loginRequest = loginRequest)
    }

    override suspend fun refresh(refreshToken: String): LoginResponse = indiStrawApiCall {
        authAPI.refresh(refreshToken = "Bearer $refreshToken")
    }

    override suspend fun checkPhoneNumber(phoneNumber: String, type: String): Void? =
        indiStrawApiCall {
            authAPI.checkPhoneNumber(phoneNumber = phoneNumber, type = type).body()
        }

    override suspend fun checkId(id: String): Void? = indiStrawApiCall {
        authAPI.checkId(id = id).body()
    }

    override suspend fun sendCertificateNumber(phoneNumber: String): Void? = indiStrawApiCall {
        authAPI.sendCertificateNumber(phoneNumber = phoneNumber).body()
    }

    override suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String): Void? =
        indiStrawApiCall {
            authAPI.checkCertificateNumber(authCode = authCode, phoneNumber = phoneNumber).body()
        }
}