package com.danbam.indistraw.feature.mobile.movie.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.indistraw.feature.mobile.movie.all.MovieAllScreen
import com.danbam.indistraw.feature.mobile.movie.detail.MovieDetailScreen
import com.danbam.indistraw.feature.mobile.movie.make.AddActorScreen
import com.danbam.indistraw.feature.mobile.movie.make.SearchActorScreen
import com.danbam.indistraw.feature.mobile.movie.make.WriteActorScreen
import com.danbam.indistraw.feature.mobile.movie.make.WriteIntroduceScreen
import com.danbam.indistraw.feature.mobile.movie.play.MoviePlayScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.movieGraph(
    navController: NavHostController,
) {
    composable(
        route = MovieNavigationItem.Detail.route
            + MovieDeepLinkKey.MOVIE_INDEX + "{${MovieDeepLinkKey.MOVIE_INDEX}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.MOVIE_INDEX) {
                type = NavType.LongType
            }
        )
    ) {
        val movieIdx = it.arguments?.getLong(MovieDeepLinkKey.MOVIE_INDEX) ?: 0
        MovieDetailScreen(navController = navController, movieIdx = movieIdx)
    }
    composable(
        route = MovieNavigationItem.Play.route
            + MovieDeepLinkKey.MOVIE_NAME + "{${MovieDeepLinkKey.MOVIE_NAME}}"
            + MovieDeepLinkKey.MOVIE_INDEX + "{${MovieDeepLinkKey.MOVIE_INDEX}}"
            + MovieDeepLinkKey.MOVIE_URL + "{${MovieDeepLinkKey.MOVIE_URL}}"
            + MovieDeepLinkKey.MOVIE_POSITION + "{${MovieDeepLinkKey.MOVIE_POSITION}}"
            + MovieDeepLinkKey.IS_VERTICAL + "{${MovieDeepLinkKey.IS_VERTICAL}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.MOVIE_NAME) {
                type = NavType.StringType
            },
            navArgument(MovieDeepLinkKey.MOVIE_INDEX) {
                type = NavType.LongType
            },
            navArgument(MovieDeepLinkKey.MOVIE_URL) {
                type = NavType.StringType
            },
            navArgument(MovieDeepLinkKey.MOVIE_POSITION) {
                type = NavType.FloatType
            },
            navArgument(MovieDeepLinkKey.IS_VERTICAL) {
                type = NavType.BoolType
            }
        )
    ) {
        val movieName = it.arguments?.getString(MovieDeepLinkKey.MOVIE_NAME) ?: ""
        val movieUrl = it.arguments?.getString(MovieDeepLinkKey.MOVIE_URL) ?: ""
        val movieIdx = it.arguments?.getLong(MovieDeepLinkKey.MOVIE_INDEX) ?: 0
        val moviePosition = it.arguments?.getFloat(MovieDeepLinkKey.MOVIE_POSITION) ?: 0F
        val isVertical = it.arguments?.getBoolean(MovieDeepLinkKey.IS_VERTICAL) ?: false
        MoviePlayScreen(
            movieName = movieName,
            movieUrl = movieUrl,
            movieIdx = movieIdx,
            position = moviePosition,
            isVertical = isVertical,
            navController = navController
        )
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
        )
    }
}