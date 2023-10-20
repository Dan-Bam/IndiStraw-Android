package com.danbam.indistraw.core.remote.request.movie

import com.danbam.indistraw.core.domain.param.movie.MovieHistoryParam
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
