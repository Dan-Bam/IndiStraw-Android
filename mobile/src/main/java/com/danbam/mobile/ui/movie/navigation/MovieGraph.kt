package com.danbam.mobile.ui.movie.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.mobile.ui.movie.all.MovieAllScreen
import com.danbam.mobile.ui.movie.detail.MovieDetailScreen
import com.danbam.mobile.ui.movie.make.AddActorScreen
import com.danbam.mobile.ui.movie.make.SearchActorScreen
import com.danbam.mobile.ui.movie.make.WriteActorScreen
import com.danbam.mobile.ui.movie.make.WriteIntroduceScreen
import com.danbam.mobile.ui.movie.play.MoviePlayScreen
import com.google.accompanist.navigation.animation.composable

sealed class MovieNavigationItem(val route: String) {
    object MovieDetail : MovieNavigationItem("movieDetail")
    object MoviePlay : MovieNavigationItem("moviePlay")
    object MovieAll : MovieNavigationItem("movieAll")
    object MovieWriteIntroduce : MovieNavigationItem("movieWriteIntroduce")
    object MovieAddActor : MovieNavigationItem("movieAddActor")
    object MovieSearchActor : MovieNavigationItem("movieSearchActor")
    object MovieWriteActor : MovieNavigationItem("movieWriteActor")
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
    composable(route = MovieNavigationItem.MovieAll.route) {
        MovieAllScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.MovieWriteIntroduce.route) {
        WriteIntroduceScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.MovieAddActor.route) {
        AddActorScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.MovieWriteActor.route) {
        WriteActorScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.MovieSearchActor.route) {
        SearchActorScreen(navController = navController)
    }
}