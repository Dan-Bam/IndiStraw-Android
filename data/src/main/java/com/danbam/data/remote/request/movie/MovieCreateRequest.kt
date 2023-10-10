package com.danbam.data.remote.request.movie

import com.danbam.domain.param.movie.MovieCreateParam
import com.google.gson.annotations.SerializedName

data class MovieCreateRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("movieUrl")
    val movieUrl: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("movieHighlight")
    val highlight: List<String>,
    @SerializedName("director")
    val director: List<Long>,
    @SerializedName("actor")
    val actor: List<Long>,
    @SerializedName("crowdTrue")
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