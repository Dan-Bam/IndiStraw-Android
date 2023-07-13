package com.danbam.data.remote.response

import com.danbam.domain.entity.MyFundingEntity
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class MyFundingResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("amount")
    val amount: AmountResponse,
    @SerializedName("remainingDay")
    val remainingDay: Int,
    @SerializedName("fundingCount")
    val fundingCount: Long,
    @SerializedName("reward")
    val reward: List<RewardResponse>,
    @SerializedName("ordererList")
    val orderList: List<OrderResponse>
) {
    data class OrderResponse(
        @SerializedName("accountIdx")
        val accountIdx: UUID,
        @SerializedName("name")
        val name: String,
        @SerializedName("phoneNumber")
        val phoneNumber: String,
        @SerializedName("zipcode")
        val zipCode: String?,
        @SerializedName("address")
        val address: String?,
        @SerializedName("profileUrl")
        val profileUrl: String?,
    )

    data class AmountResponse(
        @SerializedName("targetAmount")
        val targetAmount: Long,
        @SerializedName("totalAmount")
        val totalAmount: Long,
        @SerializedName("percentage")
        val percentage: Double,
    )

    data class RewardResponse(
        @SerializedName("idx")
        val idx: Long,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("price")
        val price: Long,
        @SerializedName("totalCount")
        val totalCount: Long?,
        @SerializedName("imageList")
        val imageList: List<String>,
    )
}

fun MyFundingResponse.toEntity() = MyFundingEntity(
    title = title,
    thumbnailUrl = thumbnailUrl,
    amount = amount.toEntity(),
    remainingDay = remainingDay,
    fundingCount = fundingCount,
    reward = reward.map { it.toEntity() },
    orderList = orderList.map { it.toEntity() }
)

fun MyFundingResponse.AmountResponse.toEntity() = MyFundingEntity.AmountEntity(
    targetAmount = targetAmount,
    totalAmount = totalAmount,
    percentage = percentage
)

fun MyFundingResponse.RewardResponse.toEntity() = MyFundingEntity.RewardEntity(
    idx = idx,
    title = title,
    description = description,
    price = price,
    totalCount = totalCount,
    imageList = imageList
)

fun MyFundingResponse.OrderResponse.toEntity() = MyFundingEntity.OrderEntity(
    accountIdx = accountIdx,
    name = name,
    phoneNumber = phoneNumber,
    zipCode = zipCode,
    address = address,
    profileUrl = profileUrl
)