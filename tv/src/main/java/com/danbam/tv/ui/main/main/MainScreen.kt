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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.IndiStrawTvNavigationDrawer
import com.danbam.design_system.component.TvNavigationItem
import com.danbam.tv.ui.home.HomeScreen
import com.danbam.tv.ui.movie.MovieScreen
import com.danbam.tv.ui.search.SearchScreen
import com.danbam.tv.ui.setting.SettingScreen
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
    var isOpenDrawer by remember { mutableStateOf(false) }
    val drawerFocusRequest = FocusRequester()

    BackHandler {
        if (isOpenDrawer) {
            context.findActivity()?.finish()
        } else {
            drawerFocusRequest.requestFocus()
        }
    }
    IndiStrawTvBackground {
        IndiStrawTvNavigationDrawer(
            modifier = Modifier.focusRequester(focusRequester = drawerFocusRequest),
            content = {
                HomeApp(navController = navController)
            }, saveDrawerState = {
                isOpenDrawer = it
            }, currentMenu = currentMenu, onMenuSelected = {
                currentMenu = it
                navController.navigate(it.route)
            })
    }
}

@OptIn(ExperimentalAnimationApi::class)
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
            SearchScreen()
        }
        composable(route = TvNavigationItem.Home.route) {
            HomeScreen()
        }
        composable(route = TvNavigationItem.Movie.route) {
            MovieScreen()
        }
        composable(route = TvNavigationItem.Setting.route) {
            SettingScreen()
        }
    }
}