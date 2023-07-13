package com.danbam.data.remote.request

import com.danbam.domain.param.MovieHistoryParam
import com.google.gson.annotations.SerializedName

data class MovieHistoryRequest(
    @SerializedName("movie_idx")
    val movieIdx: Int,
    @SerializedName("history_time")
    val historyTime: Float,
)

fun MovieHistoryParam.toRequest() = MovieHistoryRequest(
    movieIdx = movieIdx,
    historyTime = historyTime
)
