package com.danbam.indistraw.core.remote.response.movie

import com.danbam.indistraw.core.domain.entity.movie.MovieEntity
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
