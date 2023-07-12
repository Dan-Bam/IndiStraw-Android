package com.danbam.domain.entity

import java.util.UUID

data class FundingDetailEntity(
    val title: String,
    val description: String,
    val writer: WriterEntity,
    val amount: AmountEntity,
    val remainingDay: Int,
    val fundingCount: Long,
    val reward: List<RewardEntity>,
    val status: String,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val fileList: List<String>,
) {
    data class WriterEntity(
        val idx: UUID,
        val name: String,
    )

    data class AmountEntity(
        val targetAmount: Long,
        val totalAmount: Long,
        val percentage: Double,
    )

    data class RewardEntity(
        val title: String,
        val description: String,
        val price: Long,
        val imageList: List<String>,
    )
}
