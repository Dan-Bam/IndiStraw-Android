package com.danbam.indistraw.mobile.ui.profile.profile

import com.danbam.indistraw.domain.entity.funding.FundingEntity
import com.danbam.indistraw.domain.entity.movie.MovieEntity

data class ProfileState(
    val id: String = "",
    val name: String = "",
    val profileUrl: String? = null,
    val myFundingList: List<FundingEntity> = listOf(),
    val fundingList: List<FundingEntity> = listOf(),
    val movieHistoryList: List<MovieEntity> = listOf()
)