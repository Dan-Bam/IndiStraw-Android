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
import androidx.navigation.NavHostController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.presentation.navigation.MainNavigationItem
import com.danbam.presentation.navigation.authGraph
import com.danbam.presentation.navigation.mainGraph
import com.danbam.presentation.navigation.movieGraph
import com.danbam.presentation.navigation.profileGraph
import com.danbam.presentation.navigation.signUpGraph
import com.danbam.presentation.ui.signup.SignUpViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
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
        startDestination = MainNavigationItem.Intro.route,
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
        authGraph(navController = navController)
        signUpGraph(navController = navController, signUpViewModel = signUpViewModel)
        movieGraph(navController = navController)
        profileGraph(navController = navController)
    }
}