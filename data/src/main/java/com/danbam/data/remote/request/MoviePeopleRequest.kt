package com.danbam.data.remote.request

import com.danbam.domain.param.MoviePeopleParam
import com.google.gson.annotations.SerializedName

data class MoviePeopleRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_url")
    val profileUrl: String,
)

fun MoviePeopleParam.toRequest() = MoviePeopleRequest(
    name = name,
    profileUrl = profileUrl
)