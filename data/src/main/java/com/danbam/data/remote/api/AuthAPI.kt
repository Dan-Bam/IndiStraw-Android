package com.danbam.data.remote.api

import com.danbam.data.remote.request.LoginRequest
import com.danbam.data.remote.response.LoginResponse
import retrofit2.http.POST

interface AuthAPI {
    @POST("signin")
    fun login(
        loginRequest: LoginRequest,
    ): LoginResponse
}