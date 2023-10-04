package com.danbam.data.remote.response

import com.danbam.domain.entity.DetailMovieHistoryEntity
import com.google.gson.annotations.SerializedName

data class DetailMovieHistoryResponse(
    @SerializedName("historyTime")
    val historyTime: Float,
)

fun DetailMovieHistoryResponse.toEntity() = DetailMovieHistoryEntity(
    historyTime = historyTime
)