package com.danbam.data.remote.response

import com.danbam.domain.entity.MoviePeopleEntity
import com.google.gson.annotations.SerializedName

data class MoviePeopleResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_url")
    val profileUrl: String,
)

fun MoviePeopleResponse.toEntity() = MoviePeopleEntity(
    name = name,
    profileUrl = profileUrl
)