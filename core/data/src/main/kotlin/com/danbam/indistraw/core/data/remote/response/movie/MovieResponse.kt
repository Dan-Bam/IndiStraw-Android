package com.danbam.indistraw.core.data.remote.response.movie

import com.danbam.indistraw.core.entity.movie.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("idx")
    val movieIdx: Long,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
)

fun MovieResponse.toEntity() = MovieEntity(
    movieIdx = movieIdx,
    thumbnailUrl = thumbnailUrl
)
