package com.danbam.data.remote.request.movie

import com.danbam.domain.param.movie.MoviePeopleParam
import com.google.gson.annotations.SerializedName

data class MoviePeopleRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
)

fun MoviePeopleParam.toRequest() = MoviePeopleRequest(
    name = name,
    profileUrl = profileUrl
)