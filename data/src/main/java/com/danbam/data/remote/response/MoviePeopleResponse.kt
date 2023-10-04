package com.danbam.data.remote.response

import com.danbam.domain.entity.MoviePeopleEntity
import com.google.gson.annotations.SerializedName

data class MoviePeopleResponse(
    @SerializedName("idx")
    val idx: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
)

fun MoviePeopleResponse.toEntity() = MoviePeopleEntity(
    idx = idx,
    name = name,
    profileUrl = profileUrl
)