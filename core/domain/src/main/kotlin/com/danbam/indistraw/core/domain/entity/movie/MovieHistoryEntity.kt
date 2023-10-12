package com.danbam.indistraw.core.entity.movie

data class MovieHistoryEntity(
    val movieIdx: Long,
    val thumbnailUrl: String,
    val historyTime: Float,
    val title: String,
)