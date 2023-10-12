package com.danbam.indistraw.data.repository

import com.danbam.indistraw.data.local.datasource.AuthLocalDataSource
import com.danbam.indistraw.data.remote.datasource.AuthRemoteDataSource
import com.danbam.indistraw.data.remote.request.auth.toRequest
import com.danbam.indistraw.data.remote.response.auth.LoginResponse
import com.danbam.indistraw.data.util.default
import com.danbam.indistraw.domain.exception.ExpiredTokenException
import com.danbam.indistraw.domain.param.auth.LoginParam
import com.danbam.indistraw.domain.param.auth.SignUpParam
import com.danbam.indistraw.domain.repository.AuthRepository
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

    override suspend fun logout() {
        val refreshToken = authLocalDataSource.fetchRefreshToken() ?: throw ExpiredTokenException()
        authRemoteDataSource.logout(refreshToken)
        clearToken()
    }

    override suspend fun clearToken() {
        with(authLocalDataSource) {
            clearAccessToken()
            clearRefreshToken()
            clearAccessExpiredAt()
            clearRefreshExpiredAt()
        }
    }

    private fun LoginResponse.saveToken() {
        authLocalDataSource.saveAccessToken(accessToken)
        authLocalDataSource.saveRefreshToken(refreshToken)
        authLocalDataSource.saveAccessExpiredAt(accessExpiredAt)
        authLocalDataSource.saveRefreshExpiredAt(refreshExpiredAt)
    }
}

