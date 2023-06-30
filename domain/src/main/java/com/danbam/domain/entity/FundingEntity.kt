package com.danbam.domain.entity

data class FundingEntity(
    val idx: Long,
    val title: String,
    val description: String,
    val percentage: Long,
    val thumbnail: String,
    val status: String,
)