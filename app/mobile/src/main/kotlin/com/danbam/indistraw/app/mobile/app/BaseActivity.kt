package com.danbam.indistraw.app.mobile.app

import android.app.PictureInPictureParams
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.feature.mobile.auth.navigation.authGraph
import com.danbam.indistraw.feature.mobile.auth.navigation.signUpGraph
import com.danbam.indistraw.app.mobile.ui.funding.navigation.fundingGraph
import com.danbam.indistraw.feature.mobile.main.navigation.MainNavigationItem
import com.danbam.indistraw.feature.mobile.main.navigation.mainGraph
import com.danbam.indistraw.feature.mobile.movie.navigation.MovieDeepLinkKey
import com.danbam.indistraw.feature.mobile.movie.navigation.MovieNavigationItem
import com.danbam.indistraw.feature.mobile.movie.navigation.movieGraph
import com.danbam.indistraw.feature.mobile.profile.navigation.profileGraph
import com.danbam.indistraw.feature.mobile.search.navigation.searchGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            IndiStrawTheme {
                BaseApp(navController = navController)
            }
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        navController.currentDestination?.route?.let {
            if (it.contains(com.danbam.indistraw.feature.mobile.movie.navigation.MovieNavigationItem.Play.route)) {
                val isVertical =
                    navController.currentBackStackEntry?.arguments?.getBoolean(com.danbam.indistraw.feature.mobile.movie.navigation.MovieDeepLinkKey.IS_VERTICAL)
                        ?: false
                enterPictureInPictureMode(
                    PictureInPictureParams.Builder()
                        .setAspectRatio(if (isVertical) Rational(9, 16) else Rational(16, 9))
                        .build()
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseApp(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = com.danbam.indistraw.feature.mobile.main.navigation.MainNavigationItem.Intro.route,
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
        signUpGraph(navController = navController)
        movieGraph(navController = navController)
        profileGraph(navController = navController)
        searchGraph(navController = navController)
        fundingGraph(navController = navController)
    }
}