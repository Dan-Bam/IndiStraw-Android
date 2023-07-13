package com.danbam.tv.ui.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.tv.ui.main.intro.IntroScreen
import com.danbam.tv.ui.main.login.LoginScreen
import com.danbam.tv.ui.main.main.MainScreen
import com.danbam.tv.ui.main.qr_login.QRLoginScreen
import com.danbam.tv.ui.movie.detail.MovieDetailScreen
import com.danbam.tv.ui.movie.play.MoviePlayScreen
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
    const val MOVIE_INDEX = "movieIndex"
    const val MOVIE_URL = "movieUrl"
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
                type = NavType.IntType
            }
        )) {
        val movieIndex = it.arguments?.getInt(MainDeepLinkKey.MOVIE_INDEX) ?: 0
        MovieDetailScreen(navController = navController, movieIndex = movieIndex)
    }
    composable(route = MainNavigationItem.MoviePlay.route
        + MainDeepLinkKey.MOVIE_URL + "{${MainDeepLinkKey.MOVIE_URL}}",
        arguments = listOf(
            navArgument(MainDeepLinkKey.MOVIE_URL) {
                type = NavType.StringType
            }
        )) {
        val movieUrl = it.arguments?.getString(MainDeepLinkKey.MOVIE_URL) ?: ""
        MoviePlayScreen(movieUrl = movieUrl)
    }
}