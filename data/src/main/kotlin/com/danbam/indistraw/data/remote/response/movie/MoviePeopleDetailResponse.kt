package com.danbam.indistraw.data.remote.response.movie

import com.danbam.indistraw.domain.entity.movie.MoviePeopleDetailEntity
import com.google.gson.annotations.SerializedName

data class MoviePeopleDetailResponse(
    @SerializedName("idx")
    val actorIdx: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
    @SerializedName("movieList")
    val movieList: List<MovieResponse>
)

fun MoviePeopleDetailResponse.toEntity() = MoviePeopleDetailEntity(
    actorIdx = actorIdx,
    name = name,
    profileUrl = profileUrl,
    movieList = movieList.map { it.toEntity() }
)