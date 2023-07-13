package com.danbam.data.remote.response

import com.danbam.domain.entity.MoviePeopleDetailEntity
import com.google.gson.annotations.SerializedName

data class MoviePeopleDetailResponse(
    @SerializedName("idx")
    val idx: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_url")
    val profileUrl: String,
    @SerializedName("movie_list")
    val movieList: List<MovieResponse>
)

fun MoviePeopleDetailResponse.toEntity() = MoviePeopleDetailEntity(
    idx = idx,
    name = name,
    profileUrl = profileUrl,
    movieList = movieList.map { it.toEntity() }
)