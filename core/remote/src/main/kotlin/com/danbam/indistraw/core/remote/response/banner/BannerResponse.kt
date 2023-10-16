package com.danbam.indistraw.core.remote.response.banner

import com.danbam.indistraw.core.domain.entity.banner.BannerEntity
import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
)

fun BannerResponse.toEntity() = BannerEntity(
    thumbnailUrl = thumbnailUrl
)
