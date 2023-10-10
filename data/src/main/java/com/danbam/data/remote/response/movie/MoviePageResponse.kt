package com.danbam.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MoviePageResponse(
    @SerializedName("isLast")
    val isLast: Boolean,
    @SerializedName("list")
    val list: List<MovieResponse>,
)