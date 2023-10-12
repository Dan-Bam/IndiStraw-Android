package com.danbam.indistraw.data.remote.response.funding

import com.danbam.indistraw.domain.entity.auth.FindIdEntity
import com.google.gson.annotations.SerializedName

data class FindIdResponse(
    @SerializedName("id")
    val id: String,
)

fun FindIdResponse.toEntity() = FindIdEntity(
    id = id
)