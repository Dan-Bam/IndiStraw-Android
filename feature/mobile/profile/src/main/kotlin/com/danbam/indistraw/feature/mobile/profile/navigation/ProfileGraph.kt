package com.danbam.indistraw.feature.mobile.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.indistraw.feature.mobile.profile.detail_address.DetailAddressScreen
import com.danbam.indistraw.feature.mobile.profile.edit_profile.EditProfileScreen
import com.danbam.indistraw.feature.mobile.profile.find_address.FindAddressScreen
import com.danbam.indistraw.feature.mobile.profile.profile.ProfileScreen
import com.danbam.indistraw.feature.mobile.profile.qr_login.QRLoginScreen
import com.danbam.indistraw.feature.mobile.profile.setting.SettingScreen
import com.google.accompanist.navigation.animation.composable

sealed class ProfileNavigationItem(val route: String) {
    object Profile : ProfileNavigationItem("profile")
    object Setting : ProfileNavigationItem("setting")
    object EditProfile : ProfileNavigationItem("editProfile")
    object FindAddress : ProfileNavigationItem("findAddress")
    object DetailAddress : ProfileNavigationItem("detailAddress")
    object QRLogin : ProfileNavigationItem("qrLogin")
}

object ProfileDeepLinkKey {
    const val ADDRESS = "address"
    const val ZIP_CODE = "zipCode"
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
    composable(
        route = ProfileNavigationItem.DetailAddress.route
                + ProfileDeepLinkKey.ADDRESS + "{${ProfileDeepLinkKey.ADDRESS}}"
                + ProfileDeepLinkKey.ZIP_CODE + "{${ProfileDeepLinkKey.ZIP_CODE}}",
        arguments = listOf(
            navArgument(ProfileDeepLinkKey.ADDRESS) {
                type = NavType.StringType
            },
            navArgument(ProfileDeepLinkKey.ZIP_CODE) {
                type = NavType.StringType
            }
        )
    ) {
        val address = it.arguments?.getString(ProfileDeepLinkKey.ADDRESS) ?: ""
        val zipCode = it.arguments?.getString(ProfileDeepLinkKey.ZIP_CODE) ?: ""
        DetailAddressScreen(navController = navController, address = address, zipCode = zipCode)
    }
    composable(route = ProfileNavigationItem.QRLogin.route) {
        QRLoginScreen(navController = navController)
    }
}