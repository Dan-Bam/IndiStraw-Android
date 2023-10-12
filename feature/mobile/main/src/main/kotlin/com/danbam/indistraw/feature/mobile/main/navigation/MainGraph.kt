package com.danbam.indistraw.feature.mobile.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.indistraw.feature.mobile.main.intro.IntroScreen
import com.danbam.indistraw.feature.mobile.main.main.MainScreen
import com.danbam.indistraw.feature.mobile.navigation.main.MainNavigationItem
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(route = MainNavigationItem.Intro.route) {
        IntroScreen(navController = navController)
    }
    composable(route = MainNavigationItem.Main.route) {
        MainScreen(navController = navController)
    }
}