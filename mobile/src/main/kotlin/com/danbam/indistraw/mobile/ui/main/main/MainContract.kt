package com.danbam.indistraw.mobile.ui.main.main

import com.danbam.indistraw.domain.entity.banner.BannerEntity
import com.danbam.indistraw.domain.entity.funding.FundingEntity
import com.danbam.indistraw.domain.entity.movie.MovieEntity


data class MainState(
    val profileUrl: String? = null,
    val movieList: List<MovieEntity> = listOf(),
    val fundingPopularList: List<FundingEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)