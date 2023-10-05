package com.danbam.data.remote.response

import com.danbam.domain.entity.MoviePeopleDetailEntity
import com.google.gson.annotations.SerializedName

data class MoviePeopleDetailResponse(
    @SerializedName("idx")
    val idx: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
    @SerializedName("movieList")
    val movieList: List<MovieResponse>
)

fun MoviePeopleDetailResponse.toEntity() = MoviePeopleDetailEntity(
    idx = idx,
    name = name,
    profileUrl = profileUrl,
    movieList = movieList.map { it.toEntity() }
)