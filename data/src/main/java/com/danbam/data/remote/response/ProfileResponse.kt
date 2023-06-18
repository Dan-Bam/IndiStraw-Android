package com.danbam.data.remote.response

import com.danbam.domain.entity.ProfileEntity
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String?,
)

fun ProfileResponse.toEntity() = ProfileEntity(
    id = id,
    name = name,
    profileUrl = if (profileUrl.isNullOrEmpty()) null else profileUrl
)