package com.danbam.data.remote.response.movie

import com.danbam.domain.entity.movie.MovieDetailEntity
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("movieUrl")
    val movieUrl: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("director")
    val directorList: List<MoviePeopleResponse>,
    @SerializedName("actor")
    val actorList: List<MoviePeopleResponse>,
    @SerializedName("movieHighlight")
    val highlight: List<String>,
    @SerializedName("crowdTrue")
    val isFunding: Boolean,
    @SerializedName("genre")
    val genre: String,
)

fun MovieDetailResponse.toEntity() = MovieDetailEntity(
    title = title,
    description = description,
    movieUrl = movieUrl,
    thumbnailUrl = thumbnailUrl,
    directorList = directorList.map { it.toEntity() },
    actorList = actorList.map { it.toEntity() },
    highlight = highlight,
    isFunding = isFunding,
    genre = genre
)
