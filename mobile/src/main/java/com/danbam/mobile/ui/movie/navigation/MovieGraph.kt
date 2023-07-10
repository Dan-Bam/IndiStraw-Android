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
    object Detail : MovieNavigationItem("movieDetail")
    object Play : MovieNavigationItem("moviePlay")
    object All : MovieNavigationItem("movieAll")
    object WriteIntroduce : MovieNavigationItem("movieWriteIntroduce")
    object AddActor : MovieNavigationItem("movieAddActor")
    object SearchActor : MovieNavigationItem("movieSearchActor")
    object WriteActor : MovieNavigationItem("movieWriteActor")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.movieGraph(
    navController: NavHostController,
) {
    composable(route = MovieNavigationItem.Detail.route) {
        MovieDetailScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.Play.route) {
        MoviePlayScreen()
    }
    composable(route = MovieNavigationItem.All.route) {
        MovieAllScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.WriteIntroduce.route) {
        WriteIntroduceScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.AddActor.route) {
        AddActorScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.WriteActor.route) {
        WriteActorScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.SearchActor.route) {
        SearchActorScreen(navController = navController)
    }
}