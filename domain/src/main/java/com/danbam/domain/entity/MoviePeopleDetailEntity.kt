package com.danbam.domain.entity

data class MoviePeopleDetailEntity(
    val idx: Long,
    val name: String,
    val profileUrl: String,
    val movieList: List<MovieEntity>
)
