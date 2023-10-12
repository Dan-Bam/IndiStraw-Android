package com.danbam.indistraw.data.remote.request.auth

import com.danbam.indistraw.domain.param.auth.LoginParam
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id")
    val id: String,
    val password: String,
)

fun LoginParam.toRequest() = LoginRequest(
    id = id,
    password = password
)