package com.danbam.data.remote.request.movie

import com.danbam.domain.param.movie.MovieHistoryParam
import com.google.gson.annotations.SerializedName

data class MovieHistoryRequest(
    @SerializedName("idx")
    val movieIdx: Long,
    @SerializedName("historyTime")
    val historyTime: Float,
)

fun MovieHistoryParam.toRequest() = MovieHistoryRequest(
    movieIdx = movieIdx,
    historyTime = historyTime
)