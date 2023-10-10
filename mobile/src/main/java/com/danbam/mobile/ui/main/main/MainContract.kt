package com.danbam.mobile.ui.main.main

import com.danbam.domain.entity.banner.BannerEntity
import com.danbam.domain.entity.funding.FundingEntity
import com.danbam.domain.entity.movie.MovieEntity

data class MainState(
    val profileUrl: String? = null,
    val movieList: List<MovieEntity> = listOf(),
    val fundingPopularList: List<FundingEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)