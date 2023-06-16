package com.danbam.presentation.ui.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.presentation.ui.main.intro.IntroScreen
import com.danbam.presentation.ui.main.main.MainScreen
import com.google.accompanist.navigation.animation.composable

sealed class MainNavigationItem(val route: String) {
    object Intro : MainNavigationItem("intro")
    object Main : MainNavigationItem("main")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(route = MainNavigationItem.Intro.route) {
        IntroScreen(navController = navController)
    }
    composable(route = MainNavigationItem.Main.route) {
        MainScreen(navController = navController)
    }
}