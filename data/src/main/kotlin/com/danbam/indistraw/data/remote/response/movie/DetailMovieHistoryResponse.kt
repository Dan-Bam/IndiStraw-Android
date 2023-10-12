package com.danbam.indistraw.data.remote.response.movie

import com.danbam.indistraw.domain.entity.movie.DetailMovieHistoryEntity
import com.google.gson.annotations.SerializedName

data class DetailMovieHistoryResponse(
    @SerializedName("historyTime")
    val historyTime: Float,
)

fun DetailMovieHistoryResponse.toEntity() = DetailMovieHistoryEntity(
    historyTime = historyTime
)