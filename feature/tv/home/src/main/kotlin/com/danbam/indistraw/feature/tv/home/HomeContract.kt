package com.danbam.indistraw.feature.tv.home

import com.danbam.indistraw.core.domain.entity.banner.BannerEntity
import com.danbam.indistraw.core.domain.entity.movie.MovieEntity

data class HomeState(
    val currentMovieIndex: Long? = null,
    val movieList: List<MovieEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)