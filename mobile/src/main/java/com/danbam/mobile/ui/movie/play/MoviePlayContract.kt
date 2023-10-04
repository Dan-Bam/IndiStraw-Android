package com.danbam.mobile.ui.movie.play

sealed class MoviePlaySideEffect {
    object SuccessSaveHistory: MoviePlaySideEffect()
}