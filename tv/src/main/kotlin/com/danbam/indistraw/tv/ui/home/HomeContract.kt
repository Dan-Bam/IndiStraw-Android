package com.danbam.indistraw.tv.ui.home

import com.danbam.indistraw.domain.entity.banner.BannerEntity
import com.danbam.indistraw.domain.entity.movie.MovieEntity


data class HomeState(
    val currentMovieIndex: Long = 0,
    val movieList: List<MovieEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)