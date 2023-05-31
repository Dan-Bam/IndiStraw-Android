package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.LoginRequest
import com.danbam.data.remote.response.LoginResponse

interface AuthRemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}