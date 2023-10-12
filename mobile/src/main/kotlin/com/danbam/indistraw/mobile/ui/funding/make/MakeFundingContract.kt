package com.danbam.indistraw.mobile.ui.funding.make

import com.danbam.indistraw.core.param.funding.FundingCreateParam
import java.time.LocalDate

data class MakeFundingState(
    val title: String = "",
    val description: String = "",
    val targetAmount: Long = 0,
    val rewardList: List<FundingCreateParam.RewardParam> = listOf(),
    val endDate: LocalDate = LocalDate.now(),
    val thumbnailUrl: String? = null,
    val imageList: List<String> = listOf(),
    val fileList: List<String> = listOf()
)