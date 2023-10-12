package com.danbam.indistraw.core.data.remote.request.auth

import com.danbam.indistraw.core.param.auth.ChangePasswordParam
import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("newPassword")
    val password: String,
)

fun ChangePasswordParam.toRequest() = ChangePasswordRequest(
    phoneNumber = phoneNumber,
    password = password
)