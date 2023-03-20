package com.danbam.indi_straw.util

sealed class AppNavigationItem(val route: String) {
    object Login : AppNavigationItem("login")
}