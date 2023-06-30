package com.danbam.mobile.ui.main.main

import com.danbam.domain.entity.FundingEntity

data class MainState(
    val profileUrl: String? = null,
    val fundingPopularList: List<FundingEntity> = listOf()
)