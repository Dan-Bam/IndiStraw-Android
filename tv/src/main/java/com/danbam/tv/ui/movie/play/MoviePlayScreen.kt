package com.danbam.tv.ui.movie.play

import androidx.compose.runtime.Composable
import com.danbam.design_system.component.IndiStrawPlayer
import com.danbam.design_system.component.IndiStrawTvBackground

@Composable
fun MoviePlayScreen(

) {
    IndiStrawTvBackground {
        IndiStrawPlayer(videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
    }
}