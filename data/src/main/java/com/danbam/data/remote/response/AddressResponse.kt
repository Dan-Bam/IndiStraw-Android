package com.danbam.data.remote.response

import com.danbam.domain.entity.AddressEntity
import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("results")
    val results: Results,
) {
    data class Results(
        @SerializedName("juso")
        val juso: List<Juso>,
    ) {
        data class Juso(
            @SerializedName("zipNo")
            val zipNo: String,
            @SerializedName("roadAddrPart1")
            val roadAddr: String,
            @SerializedName("bdNm")
            val buildName: String,
        )
    }
}

fun AddressResponse.Results.Juso.toEntity() = AddressEntity(
    zipcode = zipNo,
    streetAddress = roadAddr,
    buildName = buildName
)
