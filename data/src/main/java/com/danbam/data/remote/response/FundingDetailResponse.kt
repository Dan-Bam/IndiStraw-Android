package com.danbam.data.remote.response

import com.danbam.domain.entity.FundingDetailEntity
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class FundingDetailResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("writer")
    val writer: WriterResponse,
    @SerializedName("amount")
    val amount: AmountResponse,
    @SerializedName("remainingDay")
    val remainingDay: Int,
    @SerializedName("fundingCount")
    val fundingCount: Long,
    @SerializedName("reward")
    val reward: List<RewardResponse>,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("imageList")
    val imageList: List<String>,
    @SerializedName("fileList")
    val fileList: List<String>,
) {
    data class WriterResponse(
        @SerializedName("idx")
        val idx: UUID,
        @SerializedName("name")
        val name: String,
    )

    data class AmountResponse(
        @SerializedName("targetAmount")
        val targetAmount: Float,
        @SerializedName("totalAmount")
        val totalAmount: Float,
        @SerializedName("percentage")
        val percentage: Long,
    )

    data class RewardResponse(
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("price")
        val price: Long,
        @SerializedName("imageUrl")
        val imageUrl: String,
    )
}

fun FundingDetailResponse.toEntity() = FundingDetailEntity(
    title = title,
    description = description,
    writer = writer.toEntity(),
    amount = amount.toEntity(),
    remainingDay = remainingDay,
    fundingCount = fundingCount,
    reward = reward.map { it.toEntity() },
    status = status,
    thumbnailUrl = thumbnailUrl,
    imageList = imageList,
    fileList = fileList
)

fun FundingDetailResponse.WriterResponse.toEntity() = FundingDetailEntity.WriterEntity(
    idx = idx,
    name = name
)

fun FundingDetailResponse.AmountResponse.toEntity() = FundingDetailEntity.AmountEntity(
    targetAmount = targetAmount,
    totalAmount = totalAmount,
    percentage = percentage
)

fun FundingDetailResponse.RewardResponse.toEntity() = FundingDetailEntity.RewardEntity(
    title = title,
    description = description,
    price = price,
    imageUrl = imageUrl
)