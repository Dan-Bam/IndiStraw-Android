package com.danbam.data.remote.response

import com.danbam.domain.entity.MovieHistoryEntity
import com.google.gson.annotations.SerializedName

data class MovieHistoryResponse(
    @SerializedName("idx")
    val movieIdx: Long,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("historyTime")
    val historyTime: Float,
    @SerializedName("title")
    val title: String,
)

fun MovieHistoryResponse.toEntity() = MovieHistoryEntity(
    movieIdx = movieIdx,
    thumbnailUrl = thumbnailUrl,
    historyTime = historyTime,
    title = title
)