package com.danbam.indistraw.domain.entity.movie

data class MoviePeopleDetailEntity(
    val actorIdx: Long,
    val name: String,
    val profileUrl: String,
    val movieList: List<MovieEntity>
)
