package com.danbam.tv.ui.movie.play

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.danbam.design_system.component.IndiStrawPlayer
import com.danbam.design_system.component.IndiStrawTvBackground

@Composable
fun MoviePlayScreen(
    movieIdx: Int,
    movieUrl: String,
    position: Float,
    moviePlayViewModel: MoviePlayViewModel = hiltViewModel()
) {
    IndiStrawTvBackground {
        IndiStrawPlayer(videoUrl = movieUrl, position = position) {
            moviePlayViewModel.addMovieHistory(movieIdx = movieIdx, it / 1000F)
        }
    }
}