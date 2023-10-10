package com.danbam.mobile.ui.movie.detail

import com.danbam.domain.entity.movie.MovieDetailEntity
import com.danbam.domain.entity.movie.MovieEntity

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
    val appearanceMovieList: List<MovieEntity> = listOf(),
    val moviePosition: Float = 0F
)