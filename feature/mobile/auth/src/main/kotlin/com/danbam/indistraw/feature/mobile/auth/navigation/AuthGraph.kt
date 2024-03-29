package com.danbam.indistraw.feature.mobile.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.indistraw.feature.mobile.auth.certificate.CertificateScreen
import com.danbam.indistraw.feature.mobile.auth.find.find_id.FindIdScreen
import com.danbam.indistraw.feature.mobile.auth.find.find_password.FindPasswordScreen
import com.danbam.indistraw.feature.mobile.auth.login.LoginScreen
import com.danbam.indistraw.feature.mobile.auth.signup.SetIdScreen
import com.danbam.indistraw.feature.mobile.auth.signup.SetNameScreen
import com.danbam.indistraw.feature.mobile.auth.signup.SetPasswordScreen
import com.danbam.indistraw.feature.mobile.auth.signup.SetProfileScreen
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.auth.CertificateType
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authGraph(navController: NavHostController) {
    composable(route = AuthNavigationItem.Login.route) {
        LoginScreen(navController = navController)
    }
    composable(
        route = AuthNavigationItem.Certificate.route
                + AuthDeepLinkKey.CERTIFICATE_TYPE + "{${AuthDeepLinkKey.CERTIFICATE_TYPE}}",
        arguments = listOf(
            navArgument(AuthDeepLinkKey.CERTIFICATE_TYPE) {
                type = NavType.StringType
            }
        )
    ) {
        val certificateType =
            it.arguments?.getString(AuthDeepLinkKey.CERTIFICATE_TYPE) ?: CertificateType.SIGN_UP

        CertificateScreen(navController = navController, certificateType = certificateType)
    }
    composable(
        route = AuthNavigationItem.FindId.route
                + AuthDeepLinkKey.PHONE_NUMBER + "{${AuthDeepLinkKey.PHONE_NUMBER}}",
        arguments = listOf(
            navArgument(AuthDeepLinkKey.PHONE_NUMBER) {
                type = NavType.StringType
            }
        )
    ) {
        val phoneNumber = it.arguments?.getString(AuthDeepLinkKey.PHONE_NUMBER) ?: ""
        FindIdScreen(navController = navController, phoneNumber = phoneNumber)
    }
    composable(
        route = AuthNavigationItem.FindPassword.route
                + AuthDeepLinkKey.PHONE_NUMBER + "{${AuthDeepLinkKey.PHONE_NUMBER}}"
                + AuthDeepLinkKey.IS_FIND_PASSWORD + "{${AuthDeepLinkKey.IS_FIND_PASSWORD}}",
        arguments = listOf(
            navArgument(AuthDeepLinkKey.PHONE_NUMBER) {
                type = NavType.StringType
            },
            navArgument(AuthDeepLinkKey.IS_FIND_PASSWORD) {
                type = NavType.BoolType
            }
        )
    ) {
        val phoneNumber = it.arguments?.getString(AuthDeepLinkKey.PHONE_NUMBER) ?: ""
        val isFindPassword = it.arguments?.getBoolean(AuthDeepLinkKey.IS_FIND_PASSWORD) ?: true
        FindPasswordScreen(
            navController = navController,
            phoneNumber = phoneNumber,
            isFindPassword = isFindPassword
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signUpGraph(
    navController: NavHostController,
) {
    composable(
        route = AuthNavigationItem.SetName.route
    ) {
        SetNameScreen(navController = navController)
    }
    composable(
        route = AuthNavigationItem.SetProfile.route
                + AuthDeepLinkKey.PHONE_NUMBER + "{${AuthDeepLinkKey.PHONE_NUMBER}}",
        arguments = listOf(
            navArgument(AuthDeepLinkKey.PHONE_NUMBER) {
                type = NavType.StringType
            }
        )) {
        val phoneNumber = it.arguments?.getString(AuthDeepLinkKey.PHONE_NUMBER) ?: ""
        SetProfileScreen(
            navController = navController,
            phoneNumber = phoneNumber
        )
    }
    composable(route = AuthNavigationItem.SetId.route) {
        SetIdScreen(navController = navController)
    }
    composable(route = AuthNavigationItem.SetPassword.route) {
        SetPasswordScreen(navController = navController)
    }
}