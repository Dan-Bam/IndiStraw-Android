package com.danbam.data.remote.response

import com.danbam.domain.entity.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("movie_idx")
    val idx: Int,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
)

fun MovieResponse.toEntity() = MovieEntity(
    idx = idx,
    thumbnailUrl = thumbnailUrl
)
