package com.danbam.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.design_system.IndiStrawTheme
import com.danbam.presentation.ui.certificate.CertificateScreen
import com.danbam.presentation.ui.find.FindIdScreen
import com.danbam.presentation.ui.find.FindPasswordScreen
import com.danbam.presentation.ui.intro.IntroScreen
import com.danbam.presentation.ui.login.LoginScreen
import com.danbam.presentation.ui.signup.SetIdScreen
import com.danbam.presentation.ui.signup.SetNameScreen
import com.danbam.presentation.ui.signup.SetPasswordScreen
import com.danbam.presentation.ui.signup.SetProfileScreen
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.CertificateType
import com.danbam.presentation.util.DeepLinkKey
import com.danbam.presentation.util.SignUpNavigationItem
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            IndiStrawTheme {
                BaseApp(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseApp(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppNavigationItem.Intro.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500
                )
            )
        },
        popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 500)) },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500
                )
            )
        }
    ) {
        mainGraph(navController = navController)
        signUpGraph(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(route = AppNavigationItem.Intro.route) {
        IntroScreen(navController = navController)
    }
    composable(route = AppNavigationItem.Login.route) {
        LoginScreen(navController = navController)
    }
    composable(
        route = AppNavigationItem.Certificate.route
                + DeepLinkKey.CERTIFICATE_TYPE + "{${DeepLinkKey.CERTIFICATE_TYPE}}",
        arguments = listOf(
            navArgument(DeepLinkKey.CERTIFICATE_TYPE) {
                type = NavType.StringType
            }
        )
    ) {
        val certificateType =
            it.arguments?.getString(DeepLinkKey.CERTIFICATE_TYPE) ?: CertificateType.SIGN_UP

        CertificateScreen(navController = navController, certificateType = certificateType)
    }
    composable(
        route = AppNavigationItem.FindId.route
                + DeepLinkKey.PHONE_NUMBER + "{${DeepLinkKey.PHONE_NUMBER}}",
        arguments = listOf(
            navArgument(DeepLinkKey.PHONE_NUMBER) {
                type = NavType.StringType
            }
        )
    ) {
        val phoneNumber = it.arguments?.getString(DeepLinkKey.PHONE_NUMBER) ?: ""
        FindIdScreen(navController = navController, phoneNumber = phoneNumber)
    }
    composable(
        route = AppNavigationItem.FindPassword.route
                + DeepLinkKey.PHONE_NUMBER + "{${DeepLinkKey.PHONE_NUMBER}}",
        arguments = listOf(
            navArgument(DeepLinkKey.PHONE_NUMBER) {
                type = NavType.StringType
            }
        )
    ) {
        val phoneNumber = it.arguments?.getString(DeepLinkKey.PHONE_NUMBER) ?: ""
        FindPasswordScreen(navController = navController, phoneNumber = phoneNumber)
    }
    composable(route = AppNavigationItem.Main.route) {
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signUpGraph(navController: NavHostController) {
    composable(
        route = SignUpNavigationItem.SetName.route
    ) {
        SetNameScreen(navController = navController)
    }
    composable(route = SignUpNavigationItem.SetProfile.route) {
        SetProfileScreen(navController = navController)
    }
    composable(
        route = SignUpNavigationItem.SetId.route,
        arguments = listOf(
            navArgument(DeepLinkKey.PHONE_NUMBER) {
                type = NavType.StringType
            }
        )
    ) {
        val phoneNumber = it.arguments?.getString(DeepLinkKey.PHONE_NUMBER) ?: ""
        SetIdScreen(navController = navController, phoneNumber = phoneNumber)
    }
    composable(route = SignUpNavigationItem.SetPassword.route) {
        SetPasswordScreen(navController = navController)
    }
}