package com.danbam.data.remote.response

import com.danbam.domain.entity.FundingEntity
import com.google.gson.annotations.SerializedName

data class FundingResponse(
    @SerializedName("idx")
    val idx: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("percentage")
    val percentage: Double,
    @SerializedName("thumbnailUrl")
    val thumbnail: String,
    @SerializedName("status")
    val status: String,
)

fun FundingResponse.toEntity() = FundingEntity(
    idx = idx,
    title = title,
    description = description,
    percentage = percentage,
    thumbnail = thumbnail,
    status = status
)