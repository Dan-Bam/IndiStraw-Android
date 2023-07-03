package com.danbam.data.remote.request

import com.danbam.domain.param.FundingCreateParam
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class FundingCreateRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("targetAmount")
    val targetAmount: Long,
    @SerializedName("directorAccount")
    val directorAccount: DirectorAccountRequest,
    @SerializedName("reward")
    val reward: List<RewardRequest>,
    @SerializedName("endDate")
    val endDate: LocalDate,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("imageList")
    val imageList: List<String>,
    @SerializedName("fileList")
    val fileList: List<String>,
) {
    data class DirectorAccountRequest(
        @SerializedName("bank")
        val bank: String,
        @SerializedName("account")
        val account: String,
    )

    data class RewardRequest(
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("price")
        val price: Long,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("isReal")
        val isReal: Boolean,
        @SerializedName("totalCount")
        val totalCount: Long?,
    )
}

fun FundingCreateParam.toRequest() = FundingCreateRequest(
    title = title,
    description = description,
    targetAmount = targetAmount,
    directorAccount = directorAccount.toRequest(),
    reward = reward.map { it.toRequest() },
    endDate = endDate,
    thumbnailUrl = thumbnailUrl,
    imageList = imageList,
    fileList = fileList
)

fun FundingCreateParam.DirectorAccountParam.toRequest() =
    FundingCreateRequest.DirectorAccountRequest(
        bank = bank,
        account = account
    )

fun FundingCreateParam.RewardParam.toRequest() = FundingCreateRequest.RewardRequest(
    title = title,
    description = description,
    price = price,
    imageUrl = imageUrl,
    isReal = isReal,
    totalCount = totalCount
)