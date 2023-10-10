package com.danbam.data.remote.response

import com.danbam.domain.entity.BannerEntity
import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
)

fun BannerResponse.toEntity() = BannerEntity(
    thumbnailUrl = thumbnailUrl
)
