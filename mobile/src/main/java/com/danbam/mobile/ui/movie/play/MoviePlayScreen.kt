package com.danbam.mobile.ui.movie.play

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import com.danbam.design_system.component.IndiStrawPlayer
import com.danbam.mobile.util.view.LockScreenOrientation

@Composable
fun MoviePlayScreen(
    movieUrl: String
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    IndiStrawPlayer(videoUrl = movieUrl)
}