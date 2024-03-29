package com.danbam.indistraw.core.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MoviePageResponse(
    @SerializedName("isLast")
    val isLast: Boolean,
    @SerializedName("list")
    val list: List<MovieResponse>,
)