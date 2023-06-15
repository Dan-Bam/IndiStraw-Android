package com.danbam.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.design_system.IndiStrawTheme
import com.danbam.presentation.ui.certificate.CertificateScreen
import com.danbam.presentation.ui.find.find_id.FindIdScreen
import com.danbam.presentation.ui.find.find_password.FindPasswordScreen
import com.danbam.presentation.ui.intro.IntroScreen
import com.danbam.presentation.ui.login.LoginScreen
import com.danbam.presentation.ui.main.MainScreen
import com.danbam.presentation.ui.movie.detail.MovieDetailScreen
import com.danbam.presentation.ui.movie.play.MoviePlayScreen
import com.danbam.presentation.ui.profile.ProfileScreen
import com.danbam.presentation.ui.signup.SetIdScreen
import com.danbam.presentation.ui.signup.SetNameScreen
import com.danbam.presentation.ui.signup.SetPasswordScreen
import com.danbam.presentation.ui.signup.SetProfileScreen
import com.danbam.presentation.ui.signup.SignUpViewModel
import com.danbam.presentation.util.view.AppNavigationItem
import com.danbam.presentation.util.view.CertificateType
import com.danbam.presentation.util.view.DeepLinkKey
import com.danbam.presentation.util.view.MovieNavigationItem
import com.danbam.presentation.util.view.SignUpNavigationItem
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
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
    val signUpViewModel: SignUpViewModel = hiltViewModel()
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
        signUpGraph(navController = navController, signUpViewModel = signUpViewModel)
        movieGraph(navController = navController)
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
        MainScreen(navController = navController)
    }
    composable(route = AppNavigationItem.Profile.route) {
        ProfileScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signUpGraph(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel,
) {
    composable(
        route = SignUpNavigationItem.SetName.route
    ) {
        SetNameScreen(navController = navController, signUpViewModel = signUpViewModel)
    }
    composable(
        route = SignUpNavigationItem.SetProfile.route
                + DeepLinkKey.PHONE_NUMBER + "{${DeepLinkKey.PHONE_NUMBER}}",
        arguments = listOf(
            navArgument(DeepLinkKey.PHONE_NUMBER) {
                type = NavType.StringType
            }
        )) {
        val phoneNumber = it.arguments?.getString(DeepLinkKey.PHONE_NUMBER) ?: ""
        SetProfileScreen(
            navController = navController,
            signUpViewModel = signUpViewModel,
            phoneNumber = phoneNumber
        )
    }
    composable(route = SignUpNavigationItem.SetId.route) {
        SetIdScreen(navController = navController, signUpViewModel = signUpViewModel)
    }
    composable(route = SignUpNavigationItem.SetPassword.route) {
        SetPasswordScreen(navController = navController, signUpViewModel = signUpViewModel)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.movieGraph(
    navController: NavHostController,
) {

    composable(route = MovieNavigationItem.MovieDetail.route) {
        MovieDetailScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.MoviePlay.route) {
        MoviePlayScreen()
    }
}