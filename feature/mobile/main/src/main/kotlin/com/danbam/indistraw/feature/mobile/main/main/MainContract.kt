package com.danbam.indistraw.feature.mobile.main.main

import com.danbam.indistraw.core.entity.banner.BannerEntity
import com.danbam.indistraw.core.entity.funding.FundingEntity
import com.danbam.indistraw.core.entity.movie.MovieEntity


data class MainState(
    val profileUrl: String? = null,
    val movieList: List<MovieEntity> = listOf(),
    val fundingPopularList: List<FundingEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)