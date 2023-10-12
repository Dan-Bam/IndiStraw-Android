package com.danbam.indistraw.tv.ui.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.indistraw.tv.ui.main.intro.IntroScreen
import com.danbam.indistraw.tv.ui.main.login.LoginScreen
import com.danbam.indistraw.tv.ui.main.main.MainScreen
import com.danbam.indistraw.tv.ui.main.qr_login.QRLoginScreen
import com.danbam.indistraw.tv.ui.movie.detail.MovieDetailScreen
import com.danbam.indistraw.tv.ui.movie.play.MoviePlayScreen
import com.google.accompanist.navigation.animation.composable

sealed class MainNavigationItem(val route: String) {
    object Intro : MainNavigationItem("intro")
    object Login : MainNavigationItem("login")
    object QRLogin : MainNavigationItem("QRLogin")
    object Main : MainNavigationItem("main")
    object MovieDetail : MainNavigationItem("movieDetail")
    object MoviePlay : MainNavigationItem("moviePlay")
}

object MainDeepLinkKey {
    const val MOVIE_NAME = "movieName"
    const val MOVIE_INDEX = "movieIndex"
    const val MOVIE_URL = "movieUrl"
    const val MOVIE_POSITION = "moviePosition"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(route = MainNavigationItem.Intro.route) {
        IntroScreen(navController = navController)
    }
    composable(route = MainNavigationItem.Login.route) {
        LoginScreen(navController = navController)
    }
    composable(route = MainNavigationItem.QRLogin.route) {
        QRLoginScreen(navController = navController)
    }
    composable(route = MainNavigationItem.Main.route) {
        MainScreen(navController = navController)
    }
    composable(route = MainNavigationItem.MovieDetail.route
        + MainDeepLinkKey.MOVIE_INDEX + "{${MainDeepLinkKey.MOVIE_INDEX}}",
        arguments = listOf(
            navArgument(MainDeepLinkKey.MOVIE_INDEX) {
                type = NavType.LongType
            }
        )) {
        val movieIdx = it.arguments?.getLong(MainDeepLinkKey.MOVIE_INDEX) ?: 0
        MovieDetailScreen(navController = navController, movieIdx = movieIdx)
    }
    composable(route = MainNavigationItem.MoviePlay.route
        + MainDeepLinkKey.MOVIE_NAME + "{${MainDeepLinkKey.MOVIE_NAME}}"
        + MainDeepLinkKey.MOVIE_INDEX + "{${MainDeepLinkKey.MOVIE_INDEX}}"
        + MainDeepLinkKey.MOVIE_URL + "{${MainDeepLinkKey.MOVIE_URL}}"
        + MainDeepLinkKey.MOVIE_POSITION + "{${MainDeepLinkKey.MOVIE_POSITION}}",
        arguments = listOf(
            navArgument(MainDeepLinkKey.MOVIE_NAME) {
                type = NavType.StringType
            },
            navArgument(MainDeepLinkKey.MOVIE_URL) {
                type = NavType.StringType
            },
            navArgument(MainDeepLinkKey.MOVIE_INDEX) {
                type = NavType.LongType
            },
            navArgument(MainDeepLinkKey.MOVIE_POSITION) {
                type = NavType.FloatType
            }
        )) {
        val movieName = it.arguments?.getString(MainDeepLinkKey.MOVIE_NAME) ?: ""
        val movieUrl = it.arguments?.getString(MainDeepLinkKey.MOVIE_URL) ?: ""
        val movieIdx = it.arguments?.getLong(MainDeepLinkKey.MOVIE_INDEX) ?: 0
        val moviePosition = it.arguments?.getFloat(MainDeepLinkKey.MOVIE_POSITION) ?: 0F
        MoviePlayScreen(
            movieName = movieName,
            movieUrl = movieUrl,
            movieIdx = movieIdx,
            position = moviePosition,
            navController = navController
        )
    }
}