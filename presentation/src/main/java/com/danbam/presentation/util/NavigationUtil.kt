package com.danbam.presentation.util

sealed class AppNavigationItem(val route: String) {
    object Login : AppNavigationItem("login")
}