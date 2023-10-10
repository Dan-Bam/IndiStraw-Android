package com.danbam.data.remote.request

import com.danbam.domain.param.ChangePasswordParam
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