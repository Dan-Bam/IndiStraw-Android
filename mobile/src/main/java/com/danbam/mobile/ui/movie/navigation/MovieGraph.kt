package com.danbam.mobile.ui.movie.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.mobile.ui.movie.detail.MovieDetailScreen
import com.danbam.mobile.ui.movie.play.MoviePlayScreen
import com.google.accompanist.navigation.animation.composable

sealed class MovieNavigationItem(val route: String) {
    object MovieDetail : MovieNavigationItem("movieDetail")
    object MoviePlay : MovieNavigationItem("moviePlay")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.movieGraph(
    navController: NavHostController,
) {

    composable(route = MovieNavigationItem.MovieDetail.route) {
        MovieDetailScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.MoviePlay.route) {
        MoviePlayScreen()
    }
}