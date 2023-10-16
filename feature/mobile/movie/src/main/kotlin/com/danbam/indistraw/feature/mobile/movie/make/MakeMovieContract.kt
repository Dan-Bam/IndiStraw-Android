package com.danbam.indistraw.feature.mobile.movie.make

import com.danbam.indistraw.core.domain.entity.movie.MoviePeopleEntity

data class MakeMovieState(
    val thumbnailUrl: String? = null,
    val movieUrl: String? = null,
    val title: String = "",
    val description: String = "",
    val isFunding: Boolean = false,
    val imageList: List<String> = listOf(),
    val directorList: List<MoviePeopleEntity> = listOf(),
    val actorList: List<MoviePeopleEntity> = listOf(),
    val searchMoviePeopleList: List<MoviePeopleEntity> = listOf()
)

sealed class MakeMovieSideEffect {
    object Next : MakeMovieSideEffect()
    object SuccessCreateMovie : MakeMovieSideEffect()
}