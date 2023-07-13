package com.danbam.data.remote.request

import com.danbam.domain.param.MovieCreateParam
import com.google.gson.annotations.SerializedName

data class MovieCreateRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("movie_url")
    val movieUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("movie_highlight")
    val highlight: List<String>,
    @SerializedName("director")
    val director: List<Int>,
    @SerializedName("actor")
    val actor: List<Int>,
    @SerializedName("crowd_true")
    val isMakeFunding: Boolean,
)

fun MovieCreateParam.toRequest() = MovieCreateRequest(
    title = title,
    description = description,
    movieUrl = movieUrl,
    thumbnailUrl = thumbnailUrl,
    highlight = highlight,
    director = director,
    actor = actor,
    isMakeFunding = isMakeFunding
)