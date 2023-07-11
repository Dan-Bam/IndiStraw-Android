package com.danbam.tv.ui.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.tv.ui.main.login.LoginScreen
import com.danbam.tv.ui.main.main.MainScreen
import com.danbam.tv.ui.main.qr_login.QRLoginScreen
import com.danbam.tv.ui.movie.detail.MovieDetailScreen
import com.google.accompanist.navigation.animation.composable

sealed class MainNavigationItem(val route: String) {
    object Login : MainNavigationItem("login")
    object QRLogin : MainNavigationItem("QRLogin")
    object Main : MainNavigationItem("main")
    object MovieDetail : MainNavigationItem("movieDetail")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(route = MainNavigationItem.Login.route) {
        LoginScreen(navController = navController)
    }
    composable(route = MainNavigationItem.QRLogin.route) {
        QRLoginScreen(navController = navController)
    }
    composable(route = MainNavigationItem.Main.route) {
        MainScreen(navController = navController)
    }
    composable(route = MainNavigationItem.MovieDetail.route) {
        MovieDetailScreen()
    }
}