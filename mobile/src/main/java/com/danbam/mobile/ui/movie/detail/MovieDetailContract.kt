package com.danbam.mobile.ui.movie.detail

import com.danbam.domain.entity.MovieDetailEntity
import com.danbam.domain.entity.MovieEntity

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
        genre = listOf()
    ),
    val appearanceMovieList: List<MovieEntity> = listOf()
)