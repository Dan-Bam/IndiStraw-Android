package com.danbam.data.repository

import com.danbam.data.local.datasource.AuthLocalDataSource
import com.danbam.data.remote.datasource.AuthRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.data.remote.response.LoginResponse
import com.danbam.data.util.default
import com.danbam.domain.exception.ExpiredTokenException
import com.danbam.domain.param.LoginParam
import com.danbam.domain.param.SignUpParam
import com.danbam.domain.repository.AuthRepository
import java.time.LocalDateTime
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun signup(signUpParam: SignUpParam) =
        authRemoteDataSource.signup(signUpRequest = signUpParam.toRequest())

    override suspend fun login(loginParam: LoginParam) =
        authRemoteDataSource.login(loginParam.toRequest()).saveToken()

    override suspend fun isLogin() {
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
            authRemoteDataSource.refresh(authLocalDataSource.fetchRefreshToken()!!).saveToken()
        }
    }

    override suspend fun checkPhoneNumber(phoneNumber: String, type: String) =
        authRemoteDataSource.checkPhoneNumber(phoneNumber = phoneNumber, type = type)

    override suspend fun checkId(id: String) =
        authRemoteDataSource.checkId(id = id)

    override suspend fun sendCertificateNumber(phoneNumber: String) =
        authRemoteDataSource.sendCertificateNumber(phoneNumber = phoneNumber)

    override suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String) =
        authRemoteDataSource.checkCertificateNumber(authCode = authCode, phoneNumber = phoneNumber)

    private fun LoginResponse.saveToken() {
        authLocalDataSource.saveAccessToken(accessToken)
        authLocalDataSource.saveRefreshToken(refreshToken)
        authLocalDataSource.saveAccessExpiredAt(accessExpiredAt)
        authLocalDataSource.saveRefreshExpiredAt(refreshExpiredAt)
    }
}

