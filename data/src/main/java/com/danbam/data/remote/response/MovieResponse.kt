package com.danbam.data.remote.response

import com.danbam.domain.entity.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("idx")
    val idx: Long,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
)

fun MovieResponse.toEntity() = MovieEntity(
    idx = idx,
    thumbnailUrl = thumbnailUrl
)
