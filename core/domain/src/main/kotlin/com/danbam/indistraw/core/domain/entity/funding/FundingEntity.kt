package com.danbam.indistraw.core.domain.entity.funding

data class FundingEntity(
    val idx: Long,
    val title: String,
    val description: String,
    val percentage: Double,
    val thumbnail: String,
    val status: String,
)