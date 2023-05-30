package com.danbam.data.repository

import com.danbam.data.remote.datasource.AuthRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.domain.entity.LoginEntity
import com.danbam.domain.param.LoginParam
import com.danbam.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun login(loginParam: LoginParam): LoginEntity =
        authRemoteDataSource.login(loginParam.toRequest()).toEntity()
}