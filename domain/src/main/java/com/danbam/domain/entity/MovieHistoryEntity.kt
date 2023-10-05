package com.danbam.domain.entity

data class MovieHistoryEntity(
    val movieIdx: Long,
    val thumbnailUrl: String,
    val historyTime: Float,
    val title: String,
)