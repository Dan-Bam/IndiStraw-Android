package com.danbam.data.remote.request

import com.danbam.domain.param.LoginParam
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