package com.danbam.tv.ui.movie.play

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawPlayer
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.tv.util.android.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun MoviePlayScreen(
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
        IndiStrawPlayer(videoUrl = movieUrl, position = position) {
            moviePlayViewModel.addMovieHistory(movieIdx = movieIdx, it / 1000F)
        }
    }
}