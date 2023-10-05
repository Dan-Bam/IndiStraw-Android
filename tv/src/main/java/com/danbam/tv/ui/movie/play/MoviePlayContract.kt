package com.danbam.tv.ui.movie.play

sealed class MoviePlaySideEffect {
    object SuccessSaveHistory: MoviePlaySideEffect()
}