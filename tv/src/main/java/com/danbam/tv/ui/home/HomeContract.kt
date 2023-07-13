package com.danbam.tv.ui.home

import com.danbam.domain.entity.MovieEntity

data class HomeState(
    val currentMovieIndex: Int = 0,
    val movieList: List<MovieEntity> = listOf(),
)