package com.danbam.mobile.ui.funding.make

import java.time.LocalDate

data class MakeFundingState(
    val title: String = "",
    val description: String = "",
    val targetAmount: String = "",
    val endDate: LocalDate = LocalDate.now(),
    val thumbnailUrl: String? = null,
    val imageList: List<String> = listOf(),
    val fileList: List<String> = listOf()
)