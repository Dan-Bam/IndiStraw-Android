package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.api.AuthAPI
import com.danbam.indistraw.core.remote.request.auth.LoginRequest
import com.danbam.indistraw.core.remote.request.auth.SignUpRequest
import com.danbam.indistraw.core.remote.response.auth.LoginResponse
import com.danbam.indistraw.core.remote.util.errorHandling
import com.danbam.indistraw.core.remote.util.indiStrawApiCall
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI,
) : AuthRemoteDataSource {
    override suspend fun signup(signUpRequest: SignUpRequest) =
        indiStrawApiCall {
            authAPI.signup(signUpRequest = signUpRequest)
        }

    override suspend fun login(loginRequest: LoginRequest): LoginResponse =
        indiStrawApiCall {
            authAPI.login(loginRequest = loginRequest)
        }

    override suspend fun refresh(refreshToken: String): LoginResponse =
        indiStrawApiCall {
            authAPI.refresh(refreshToken = "Bearer $refreshToken")
        }

    override suspend fun checkPhoneNumber(phoneNumber: String, type: String) =
        indiStrawApiCall {
            authAPI.checkPhoneNumber(phoneNumber = phoneNumber, type = type).errorHandling()
        }

    override suspend fun checkId(id: String) =
        indiStrawApiCall {
            authAPI.checkId(id = id).errorHandling()
        }

    override suspend fun sendCertificateNumber(phoneNumber: String) =
        indiStrawApiCall {
            authAPI.sendCertificateNumber(phoneNumber = phoneNumber).errorHandling()
        }

    override suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String) =
        indiStrawApiCall {
            authAPI.checkCertificateNumber(authCode = authCode, phoneNumber = phoneNumber)
                .errorHandling()
        }

    override suspend fun logout(refreshToken: String) =
        indiStrawApiCall {
            authAPI.logout(refreshToken = "Bearer $refreshToken").errorHandling()
        }
}