package com.danbam.data.remote.request

import com.danbam.domain.param.EditProfileParam
import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("address")
    val address: AddressRequest?,
    @SerializedName("profileUrl")
    val profileUrl: String?,
) {
    data class AddressRequest(
        @SerializedName("zipcode")
        val zipcode: String,
        @SerializedName("streetAddress")
        val streetAddress: String,
        @SerializedName("detailAddress")
        val detailAddress: String?,
    )
}

fun EditProfileParam.toRequest() = EditProfileRequest(
    name = name,
    phoneNumber = phoneNumber,
    address = address?.toRequest(),
    profileUrl = profileUrl
)

fun EditProfileParam.AddressParam.toRequest() = EditProfileRequest.AddressRequest(
    zipcode = zipcode,
    streetAddress = streetAddress,
    detailAddress = detailAddress
)