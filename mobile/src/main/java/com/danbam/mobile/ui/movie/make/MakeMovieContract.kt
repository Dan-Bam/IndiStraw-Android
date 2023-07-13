package com.danbam.mobile.ui.movie.make

data class MakeMovieState(
    val thumbnailUrl: String? = null,
    val movieUrl: String? = null,
    val title: String = "",
    val description: String = "",
    val isFunding: Boolean = false,
    val imageList: List<String> = listOf()
)

sealed class MakeMovieSideEffect {
    object Next : MakeMovieSideEffect()
}