package com.danbam.mobile.ui.movie.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.mobile.ui.movie.all.MovieAllScreen
import com.danbam.mobile.ui.movie.detail.MovieDetailScreen
import com.danbam.mobile.ui.movie.make.AddActorScreen
import com.danbam.mobile.ui.movie.make.MakeMovieViewModel
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

object MovieDeepLinkKey {
    const val ADD_ACTOR_TYPE = "addActorType"
    const val MOVIE_INDEX = "movieIndex"
}

object ActorType {
    const val ACTOR = "actor"
    const val DIRECTOR = "director"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.movieGraph(
    navController: NavHostController,
    makeMovieViewModel: MakeMovieViewModel
) {
    composable(
        route = MovieNavigationItem.Detail.route
            + MovieDeepLinkKey.MOVIE_INDEX + "{${MovieDeepLinkKey.MOVIE_INDEX}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.MOVIE_INDEX) {
                type = NavType.IntType
            }
        )
    ) {
        val movieIndex = it.arguments?.getInt(MovieDeepLinkKey.MOVIE_INDEX) ?: 0
        MovieDetailScreen(navController = navController, movieIndex = movieIndex)
    }
    composable(route = MovieNavigationItem.Play.route) {
        MoviePlayScreen()
    }
    composable(route = MovieNavigationItem.All.route) {
        MovieAllScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.WriteIntroduce.route) {
        WriteIntroduceScreen(navController = navController, makeMovieViewModel = makeMovieViewModel)
    }
    composable(route = MovieNavigationItem.AddActor.route) {
        AddActorScreen(navController = navController, makeMovieViewModel = makeMovieViewModel)
    }
    composable(
        route = MovieNavigationItem.WriteActor.route
            + MovieDeepLinkKey.ADD_ACTOR_TYPE + "{${MovieDeepLinkKey.ADD_ACTOR_TYPE}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.ADD_ACTOR_TYPE) {
                type = NavType.StringType
            }
        )
    ) {
        val addActorType =
            it.arguments?.getString(MovieDeepLinkKey.ADD_ACTOR_TYPE) ?: ActorType.ACTOR
        WriteActorScreen(
            navController = navController,
            addActorType = addActorType,
            makeMovieViewModel = makeMovieViewModel
        )
    }
    composable(
        route = MovieNavigationItem.SearchActor.route
            + MovieDeepLinkKey.ADD_ACTOR_TYPE + "{${MovieDeepLinkKey.ADD_ACTOR_TYPE}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.ADD_ACTOR_TYPE) {
                type = NavType.StringType
            }
        )
    ) {
        val addActorType =
            it.arguments?.getString(MovieDeepLinkKey.ADD_ACTOR_TYPE) ?: ActorType.ACTOR
        SearchActorScreen(
            navController = navController,
            addActorType = addActorType,
            makeMovieViewModel = makeMovieViewModel
        )
    }
}