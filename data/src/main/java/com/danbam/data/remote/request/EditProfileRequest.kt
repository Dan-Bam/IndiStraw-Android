package com.danbam.data.remote.request

import com.danbam.domain.param.EditProfileParam
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