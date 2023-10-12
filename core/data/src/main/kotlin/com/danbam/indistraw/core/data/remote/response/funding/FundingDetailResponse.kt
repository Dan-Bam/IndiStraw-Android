package com.danbam.indistraw.core.data.remote.response.funding

import com.danbam.indistraw.core.entity.funding.FundingDetailEntity
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
    @SerializedName("isFunding")
    val isFunding: Boolean,
) {
    data class WriterResponse(
        @SerializedName("idx")
        val idx: UUID,
        @SerializedName("name")
        val name: String,
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
    fileList = fileList,
    isFunding = isFunding
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
    idx = idx,
    title = title,
    description = description,
    price = price,
    totalCount = totalCount,
    imageList = imageList
)