package com.danbam.indistraw.feature.mobile.navigation.main

sealed class MainNavigationItem(val route: String) {
    object Intro : MainNavigationItem("intro")
    object Main : MainNavigationItem("main")
}