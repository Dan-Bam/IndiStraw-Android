package com.danbam.indistraw.core.data.remote.response.funding

import com.danbam.indistraw.core.domain.entity.auth.FindIdEntity
import com.google.gson.annotations.SerializedName

data class FindIdResponse(
    @SerializedName("id")
    val id: String,
)

fun FindIdResponse.toEntity() = FindIdEntity(
    id = id
)