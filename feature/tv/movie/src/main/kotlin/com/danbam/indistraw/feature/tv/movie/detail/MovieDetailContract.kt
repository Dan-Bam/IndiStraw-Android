package com.danbam.indistraw.feature.tv.movie.detail

import com.danbam.indistraw.core.entity.movie.MovieDetailEntity

data class MovieDetailState(
    val movieDetailInfo: MovieDetailEntity = MovieDetailEntity(
        title = "",
        description = "",
        movieUrl = "",
        thumbnailUrl = "",
        directorList = listOf(),
        actorList = listOf(),
        highlight = listOf(),
        isFunding = false,
        genre = ""
    ),
    val moviePosition: Float = 0F
)