package com.danbam.indistraw.feature.mobile.main.main

import com.danbam.indistraw.core.domain.entity.banner.BannerEntity
import com.danbam.indistraw.core.domain.entity.funding.FundingEntity
import com.danbam.indistraw.core.domain.entity.movie.MovieEntity


data class MainState(
    val profileUrl: String? = null,
    val movieList: List<MovieEntity> = listOf(),
    val fundingPopularList: List<FundingEntity> = listOf(),
    val bannerList: List<BannerEntity> = listOf()
)