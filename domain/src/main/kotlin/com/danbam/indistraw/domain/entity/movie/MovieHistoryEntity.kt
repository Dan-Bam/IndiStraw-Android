package com.danbam.indistraw.domain.entity.movie

data class MovieHistoryEntity(
    val movieIdx: Long,
    val thumbnailUrl: String,
    val historyTime: Float,
    val title: String,
)