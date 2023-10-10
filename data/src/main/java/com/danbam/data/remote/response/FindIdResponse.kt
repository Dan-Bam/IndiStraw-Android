package com.danbam.data.remote.response

import com.danbam.domain.entity.FindIdEntity
import com.google.gson.annotations.SerializedName

data class FindIdResponse(
    @SerializedName("id")
    val id: String,
)

fun FindIdResponse.toEntity() = FindIdEntity(
    id = id
)