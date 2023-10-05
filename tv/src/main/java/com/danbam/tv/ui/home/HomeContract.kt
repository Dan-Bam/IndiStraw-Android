package com.danbam.tv.ui.home

import com.danbam.domain.entity.BannerEntity
import com.danbam.domain.entity.MovieEntity

data class HomeState(
    val currentMovieIndex: Long = 0,
    val movieList: List<MovieEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)