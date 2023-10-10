package com.danbam.mobile.ui.main.main

import com.danbam.domain.entity.BannerEntity
import com.danbam.domain.entity.FundingEntity
import com.danbam.domain.entity.MovieEntity

data class MainState(
    val profileUrl: String? = null,
    val movieList: List<MovieEntity> = listOf(),
    val fundingPopularList: List<FundingEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)