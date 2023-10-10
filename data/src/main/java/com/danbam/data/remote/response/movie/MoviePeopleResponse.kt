package com.danbam.data.remote.response.movie

import com.danbam.domain.entity.movie.MoviePeopleEntity
import com.google.gson.annotations.SerializedName

data class MoviePeopleResponse(
    @SerializedName("idx")
    val actorIdx: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
)

fun MoviePeopleResponse.toEntity() = MoviePeopleEntity(
    actorIdx = actorIdx,
    name = name,
    profileUrl = profileUrl
)