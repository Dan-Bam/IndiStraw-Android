package com.danbam.indistraw.feature.tv.movie.play

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.ui.exoplayer.IndiStrawPlayer
import com.danbam.indistraw.core.design_system.component.IndiStrawTvBackground
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun MoviePlayScreen(
    movieName: String,
    movieIdx: Long,
    movieUrl: String,
    position: Float,
    navController: NavController,
    moviePlayViewModel: MoviePlayViewModel = hiltViewModel()
) {
    val container = moviePlayViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    sideEffect.observeWithLifecycle {
        when (it) {
            is MoviePlaySideEffect.SuccessSaveHistory -> {
                navController.popBackStack()
            }
        }
    }

    IndiStrawTvBackground {
        com.danbam.indistraw.core.ui.exoplayer.IndiStrawPlayer(
            movieUrl = movieUrl,
            movieName = movieName,
            position = position,
            isMobile = false,
            isVertical = false,
            onPIP = {},
            onDispose = {
                moviePlayViewModel.addMovieHistory(movieIdx = movieIdx, it / 1000F)
            })
    }
}