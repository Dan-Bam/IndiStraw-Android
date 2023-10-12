package com.danbam.indistraw.feature.tv.navigation.main

sealed class MainNavigationItem(val route: String) {
    object Intro : MainNavigationItem("intro")
    object Login : MainNavigationItem("login")
    object QRLogin : MainNavigationItem("QRLogin")
    object Main : MainNavigationItem("main")
    object MovieDetail : MainNavigationItem("movieDetail")
    object MoviePlay : MainNavigationItem("moviePlay")
}