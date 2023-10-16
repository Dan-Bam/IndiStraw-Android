package com.danbam.indistraw.core.remote.response.movie

import com.danbam.indistraw.core.domain.entity.movie.DetailMovieHistoryEntity
import com.google.gson.annotations.SerializedName

data class DetailMovieHistoryResponse(
    @SerializedName("historyTime")
    val historyTime: Float,
)

fun DetailMovieHistoryResponse.toEntity() = DetailMovieHistoryEntity(
    historyTime = historyTime
)