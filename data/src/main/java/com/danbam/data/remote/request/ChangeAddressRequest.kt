package com.danbam.data.remote.request

import com.danbam.domain.param.ChangeAddressParam
import com.google.gson.annotations.SerializedName

data class ChangeAddressRequest(
    @SerializedName("zipcode")
    val zipCode: String,
    @SerializedName("streetAddress")
    val streetAddress: String,
    @SerializedName("detailAddress")
    val detailAddress: String,
)

fun ChangeAddressParam.toRequest() = ChangeAddressRequest(
    zipCode = zipCode,
    streetAddress = streetAddress,
    detailAddress = detailAddress
)