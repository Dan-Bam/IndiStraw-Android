package com.danbam.indistraw.mobile.ui.movie.play

import android.app.PictureInPictureParams
import android.content.pm.ActivityInfo
import android.util.Rational
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.design_system.component.IndiStrawPlayer
import com.danbam.indistraw.design_system.util.findActivity
import com.danbam.indistraw.mobile.util.android.getActivity
import com.danbam.indistraw.mobile.util.android.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun MoviePlayScreen(
    movieName: String,
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
    val context = LocalContext.current

    activity.window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

    sideEffect.observeWithLifecycle {
        when (it) {
            is MoviePlaySideEffect.SuccessSaveHistory -> {
                navController.popBackStack()
            }
        }
    }

    IndiStrawPlayer(
        movieUrl = movieUrl,
        movieName = movieName,
        position = position,
        isMobile = true,
        isVertical = isVertical,
        onPIP = {
            activity.enterPictureInPictureMode(
                PictureInPictureParams.Builder()
                    .setAspectRatio(if (isVertical) Rational(9, 16) else Rational(16, 9))
                    .build()
            )
        },
        onDispose = {
            context.findActivity()?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            moviePlayViewModel.addMovieHistory(movieIdx = movieIdx, it / 1000F)
        })
}