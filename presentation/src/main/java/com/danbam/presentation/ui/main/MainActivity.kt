package com.danbam.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.presentation.ui.intro.IntroScreen
import com.danbam.presentation.ui.login.LoginScreen
import com.danbam.presentation.ui.signup.SetProfileScreen
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.SignUpNavigationItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            IndiStrawTheme {
                BaseApp(navController = navController)
            }
        }
    }
}

@Composable
fun BaseApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppNavigationItem.Intro.route) {
        mainGraph(navController = navController)
        signUpGraph(navController = navController)
    }
}

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(route = AppNavigationItem.Intro.route) {
        IntroScreen(navController = navController)
    }
    composable(route = AppNavigationItem.Login.route) {
        LoginScreen(navController = navController)
    }
    composable(route = AppNavigationItem.FindId.route) {

    }
    composable(route = AppNavigationItem.FindPassword.route) {

    }
    composable(route = AppNavigationItem.Main.route) {

    }
}

fun NavGraphBuilder.signUpGraph(navController: NavHostController) {
    composable(route = SignUpNavigationItem.SetProfile.route) {
        SetProfileScreen(navController = navController)
    }
}