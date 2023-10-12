package com.danbam.indistraw.domain.entity.funding

import java.util.UUID

data class MyFundingEntity(
    val title: String,
    val thumbnailUrl: String,
    val amount: AmountEntity,
    val remainingDay: Int,
    val fundingCount: Long,
    val reward: List<RewardEntity>,
    val orderList: List<OrderEntity>
) {
    data class OrderEntity(
        val accountIdx: UUID,
        val name: String,
        val phoneNumber: String,
        val zipCode: String?,
        val address: String?,
        val profileUrl: String?,
    )

    data class AmountEntity(
        val targetAmount: Long,
        val totalAmount: Long,
        val percentage: Double,
    )

    data class RewardEntity(
        val idx: Long,
        val title: String,
        val description: String,
        val price: Long,
        val totalCount: Long?,
        val imageList: List<String>,
    )
}
