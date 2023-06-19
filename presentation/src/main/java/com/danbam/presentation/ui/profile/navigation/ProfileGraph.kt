package com.danbam.presentation.ui.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.presentation.ui.profile.edit_profile.EditProfileScreen
import com.danbam.presentation.ui.profile.find_address.FindAddressScreen
import com.danbam.presentation.ui.profile.profile.ProfileScreen
import com.danbam.presentation.ui.profile.setting.SettingScreen
import com.google.accompanist.navigation.animation.composable

sealed class ProfileNavigationItem(val route: String) {
    object Profile : ProfileNavigationItem("profile")
    object Setting : ProfileNavigationItem("setting")
    object EditProfile : ProfileNavigationItem("editProfile")
    object FindAddress : ProfileNavigationItem("findAddress")
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
    composable(route = ProfileNavigationItem.EditProfile.route) {
        EditProfileScreen(navController = navController)
    }
    composable(route = ProfileNavigationItem.FindAddress.route) {
        FindAddressScreen(navController = navController)
    }
}