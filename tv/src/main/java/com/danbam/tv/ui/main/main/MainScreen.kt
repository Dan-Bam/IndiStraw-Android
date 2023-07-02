package com.danbam.tv.ui.main.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.IndiStrawTvNavigationDrawer
import com.danbam.design_system.component.TvNavigationItem
import com.danbam.tv.util.android.findActivity
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val navController = rememberAnimatedNavController()
    var currentMenu: TvNavigationItem by remember { mutableStateOf(TvNavigationItem.Home) }

    BackHandler {
        context.findActivity()?.finish()
    }
    IndiStrawTvBackground {
        IndiStrawTvNavigationDrawer(content = {
            HomeApp(navController = navController)
        }, currentMenu = currentMenu, onMenuSelected = {
            currentMenu = it
            navController.navigate(it.route)
        })
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun HomeApp(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = TvNavigationItem.Home.route,
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
        composable(route = TvNavigationItem.Search.route) {

        }
        composable(route = TvNavigationItem.Home.route) {

        }
        composable(route = TvNavigationItem.Movie.route) {

        }
        composable(route = TvNavigationItem.Setting.route) {

        }
    }
}