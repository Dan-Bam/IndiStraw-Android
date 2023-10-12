package com.danbam.indistraw.feature.mobile.movie.play

sealed class MoviePlaySideEffect {
    object SuccessSaveHistory: MoviePlaySideEffect()
}