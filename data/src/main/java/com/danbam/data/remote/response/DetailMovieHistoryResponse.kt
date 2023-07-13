package com.danbam.data.remote.response

import com.danbam.domain.entity.DetailMovieHistoryEntity
import com.google.gson.annotations.SerializedName

data class DetailMovieHistoryResponse(
    @SerializedName("account_idx")
    val accountIdx: Int,
    @SerializedName("history_time")
    val historyTime: Float,
)

fun DetailMovieHistoryResponse.toEntity() = DetailMovieHistoryEntity(
    accountIdx = accountIdx,
    historyTime = historyTime
)