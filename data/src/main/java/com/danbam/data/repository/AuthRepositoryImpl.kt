package com.danbam.data.repository

import com.danbam.data.local.datasource.AuthLocalDataSource
import com.danbam.data.remote.datasource.AuthRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.param.LoginParam
import com.danbam.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun login(loginParam: LoginParam) {
        authRemoteDataSource.login(loginParam.toRequest()).toEntity().let {
            authLocalDataSource.saveAccessToken(it.accessToken)
            authLocalDataSource.saveRefreshToken(it.refreshToken)
            authLocalDataSource.saveAccessExpiredAt(it.accessExpiredAt)
            authLocalDataSource.saveRefreshExpiredAt(it.refreshExpiredAt)
        }
    }
}

