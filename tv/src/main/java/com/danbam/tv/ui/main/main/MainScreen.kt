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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.IndiStrawTvNavigationDrawer
import com.danbam.design_system.component.TvNavigationItem
import com.danbam.tv.ui.home.HomeScreen
import com.danbam.tv.ui.movie.movie.MovieScreen
import com.danbam.tv.ui.search.SearchScreen
import com.danbam.tv.ui.setting.SettingScreen
import com.danbam.tv.util.android.findActivity
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val mainNavController = rememberAnimatedNavController()
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
                HomeApp(
                    mainNavController = mainNavController,
                    navController = navController,
                    isOpenDrawer = isOpenDrawer
                )
            }, saveDrawerState = {
                isOpenDrawer = it
            }, currentMenu = currentMenu, onMenuSelected = {
                currentMenu = it
                mainNavController.navigate(it.route)
            })
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeApp(
    mainNavController: NavHostController,
    navController: NavController,
    isOpenDrawer: Boolean
) {
    AnimatedNavHost(
        navController = mainNavController,
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
            SearchScreen(navController = navController)
        }
        composable(route = TvNavigationItem.Home.route) {
            HomeScreen(navController = navController, isOpenDrawer = isOpenDrawer)
        }
        composable(route = TvNavigationItem.Movie.route) {
            MovieScreen(navController = navController, isOpenDrawer = isOpenDrawer)
        }
        composable(route = TvNavigationItem.Setting.route) {
            SettingScreen(navController = navController)
        }
    }
}