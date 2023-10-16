package com.danbam.indistraw.core.remote.response.auth

import com.danbam.indistraw.core.domain.entity.auth.ProfileEntity
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("zipcode")
    val zipcode: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("profileUrl")
    val profileUrl: String?,
)

fun ProfileResponse.toEntity() = ProfileEntity(
    id = id,
    name = name,
    phoneNumber = phoneNumber,
    zipcode = zipcode,
    address = address,
    profileUrl = profileUrl
)