package com.danbam.indistraw.data.remote.request.auth

import com.danbam.indistraw.domain.param.auth.SignUpParam
import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("profileUrl")
    val profileUrl: String?,
)

fun SignUpParam.toRequest() = SignUpRequest(
    id = id,
    password = password,
    name = name,
    phoneNumber = phoneNumber,
    profileUrl = profileUrl
)