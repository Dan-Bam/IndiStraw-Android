package com.danbam.tv.ui.movie.detail

import com.danbam.domain.entity.MovieDetailEntity

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