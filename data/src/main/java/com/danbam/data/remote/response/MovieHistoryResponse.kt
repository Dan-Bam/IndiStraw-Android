package com.danbam.data.remote.response

import com.danbam.domain.entity.MovieHistoryEntity
import com.google.gson.annotations.SerializedName

data class MovieHistoryResponse(
    @SerializedName("movie_idx")
    val movieIdx: Int,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("history_time")
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