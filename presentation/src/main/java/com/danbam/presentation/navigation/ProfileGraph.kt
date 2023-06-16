package com.danbam.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.presentation.ui.profile.ProfileScreen
import com.danbam.presentation.ui.setting.SettingScreen
import com.google.accompanist.navigation.animation.composable

sealed class ProfileNavigationItem(val route: String) {
    object Profile : ProfileNavigationItem("profile")
    object Setting : ProfileNavigationItem("setting")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(
    navController: NavHostController,
) {
    composable(route = ProfileNavigationItem.Profile.route) {
        ProfileScreen(navController = navController)
    }
    composable(route = ProfileNavigationItem.Setting.route) {
        SettingScreen(navController = navController)
    }
}