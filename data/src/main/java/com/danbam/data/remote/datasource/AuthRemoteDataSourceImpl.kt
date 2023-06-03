package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.AuthAPI
import com.danbam.data.remote.request.LoginRequest
import com.danbam.data.remote.response.LoginResponse
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI,
) : AuthRemoteDataSource {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse = indiStrawApiCall {
        authAPI.login(loginRequest = loginRequest)
    }

    override suspend fun refresh(refreshToken: String): LoginResponse = indiStrawApiCall {
        authAPI.refresh(refreshToken = refreshToken)
    }
}