package com.danbam.mobile.ui.movie.play

import android.app.PictureInPictureParams
import android.content.pm.ActivityInfo
import android.util.Rational
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
    val container = moviePlayViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow
    val activity = getActivity()

    activity.window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

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
    IndiStrawPlayer(
        videoUrl = movieUrl,
        position = position,
        onFinish = { navController.popBackStack() },
        onPIP = {
            activity.enterPictureInPictureMode(
                PictureInPictureParams.Builder()
                    .setAspectRatio(if (isVertical) Rational(9, 16) else Rational(16, 9))
                    .build()
            )
        },
        onDispose = {
            moviePlayViewModel.addMovieHistory(movieIdx = movieIdx, it / 1000F)
        })
}