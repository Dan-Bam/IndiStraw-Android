package com.danbam.indistraw.core.data.remote.response.banner

import com.danbam.indistraw.core.entity.banner.BannerEntity
import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
)

fun BannerResponse.toEntity() = BannerEntity(
    thumbnailUrl = thumbnailUrl
)
