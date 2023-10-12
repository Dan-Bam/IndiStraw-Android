package com.danbam.indistraw.feature.tv.movie.play

sealed class MoviePlaySideEffect {
    object SuccessSaveHistory: MoviePlaySideEffect()
}