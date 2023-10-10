package com.danbam.tv.ui.home

import com.danbam.domain.entity.banner.BannerEntity
import com.danbam.domain.entity.movie.MovieEntity

data class HomeState(
    val currentMovieIndex: Long = 0,
    val movieList: List<MovieEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)