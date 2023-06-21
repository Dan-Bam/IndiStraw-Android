package com.danbam.mobile.app

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
import com.danbam.mobile.ui.main.navigation.MainNavigationItem
import com.danbam.mobile.ui.auth.navigation.authGraph
import com.danbam.mobile.ui.main.navigation.mainGraph
import com.danbam.mobile.ui.movie.navigation.movieGraph
import com.danbam.mobile.ui.profile.navigation.profileGraph
import com.danbam.mobile.ui.auth.navigation.signUpGraph
import com.danbam.mobile.ui.auth.signup.SignUpViewModel
import com.danbam.mobile.ui.search.navigation.searchGraph
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
        searchGraph(navController = navController)
    }
}