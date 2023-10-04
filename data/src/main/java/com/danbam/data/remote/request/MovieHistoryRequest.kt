package com.danbam.data.remote.request

import com.danbam.domain.param.MovieHistoryParam
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
