package com.danbam.mobile.ui.movie.play

import android.content.pm.ActivityInfo
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawPlayer
import com.danbam.mobile.util.android.getActivity
import com.danbam.mobile.util.android.observeWithLifecycle
import com.danbam.mobile.util.view.LockScreenOrientation
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun MoviePlayScreen(
    movieIdx: Long,
    movieUrl: String,
    position: Float,
    isVertical: Boolean,
    navController: NavController,
    moviePlayViewModel: MoviePlayViewModel = hiltViewModel()
) {
    getActivity().window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

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

    if (!isVertical) {
        LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
    IndiStrawPlayer(videoUrl = movieUrl, position = position) {
        moviePlayViewModel.addMovieHistory(movieIdx = movieIdx, it / 1000F)
    }
}