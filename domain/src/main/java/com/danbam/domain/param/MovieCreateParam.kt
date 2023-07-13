package com.danbam.domain.param

data class MovieCreateParam(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val highlight: List<String>,
    val director: List<Int>,
    val actor: List<Int>,
    val isMakeFunding: Boolean,
)
