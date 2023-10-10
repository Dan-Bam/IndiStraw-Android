package com.danbam.data.remote.request.auth

import com.danbam.domain.param.auth.EditProfileParam
import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String?,
)

fun EditProfileParam.toRequest() = EditProfileRequest(
    name = name,
    profileUrl = profileUrl
)