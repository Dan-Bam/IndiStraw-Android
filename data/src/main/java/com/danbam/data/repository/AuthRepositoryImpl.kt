package com.danbam.data.repository

import com.danbam.data.local.datasource.AuthLocalDataSource
import com.danbam.data.remote.datasource.AuthRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.data.remote.response.LoginResponse
import com.danbam.domain.exception.ExpiredTokenException
import com.danbam.domain.param.LoginParam
import com.danbam.domain.repository.AuthRepository
import java.time.LocalDateTime
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun login(loginParam: LoginParam) {
        authRemoteDataSource.login(loginParam.toRequest()).saveToken()
    }

    override suspend fun isLogin() {
        val now = LocalDateTime.now()
        val accessExpiredAt =
            authLocalDataSource.fetchAccessExpiredAt() ?: throw ExpiredTokenException()
        val refreshExpiredAt = authLocalDataSource.fetchRefreshExpiredAt()
        if (refreshExpiredAt == null || now.isAfter(refreshExpiredAt)) {
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

    private suspend fun LoginResponse.saveToken() {
        authLocalDataSource.saveAccessToken(accessToken)
        authLocalDataSource.saveRefreshToken(refreshToken)
        authLocalDataSource.saveAccessExpiredAt(accessExpiredAt)
        authLocalDataSource.saveRefreshExpiredAt(refreshExpiredAt)
    }
}

