package com.danbam.data.remote.response

import com.danbam.domain.entity.MovieDetailEntity
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("movie_url")
    val movieUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("director")
    val directorList: List<MoviePeopleResponse>,
    @SerializedName("actor")
    val actorList: List<MoviePeopleResponse>,
    @SerializedName("movie_highlight")
    val highlight: List<String>,
    @SerializedName("clowd_true")
    val isFunding: Boolean,
    @SerializedName("genre")
    val genre: List<String>?,
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
    genre = genre ?: listOf()
)
