package com.danbam.domain.entity.movie

data class MovieDetailEntity(
    val title: String,
    val description: String,
    val movieUrl: String,
    val thumbnailUrl: String,
    val directorList: List<MoviePeopleEntity>,
    val actorList: List<MoviePeopleEntity>,
    val highlight: List<String>,
    val isFunding: Boolean,
    val genre: String,
)
