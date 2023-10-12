package com.danbam.indistraw.feature.tv.home

import com.danbam.indistraw.core.entity.banner.BannerEntity
import com.danbam.indistraw.core.entity.movie.MovieEntity


data class HomeState(
    val currentMovieIndex: Long = 0,
    val movieList: List<MovieEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)