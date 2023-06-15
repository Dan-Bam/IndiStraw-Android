package com.danbam.presentation.ui.movie.play

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import com.danbam.design_system.component.IndiStrawPlayer
import com.danbam.presentation.util.view.LockScreenOrientation

@Composable
fun MoviePlayScreen(

) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    IndiStrawPlayer(videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
}