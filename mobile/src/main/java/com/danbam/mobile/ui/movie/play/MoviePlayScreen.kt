package com.danbam.mobile.ui.movie.play

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.danbam.design_system.component.IndiStrawPlayer
import com.danbam.mobile.util.view.LockScreenOrientation

@Composable
fun MoviePlayScreen(
    movieIdx: Int,
    movieUrl: String,
    position: Float,
    moviePlayViewModel: MoviePlayViewModel = hiltViewModel()
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    IndiStrawPlayer(videoUrl = movieUrl, position = position) {
        moviePlayViewModel.addMovieHistory(movieIdx = movieIdx, it / 1000F)
    }
}