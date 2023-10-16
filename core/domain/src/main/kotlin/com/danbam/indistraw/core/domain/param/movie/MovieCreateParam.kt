package com.danbam.indistraw.core.domain.param.movie

data class MovieCreateParam(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val highlight: List<String>,
    val director: List<Long>,
    val actor: List<Long>,
    val isMakeFunding: Boolean,
)
