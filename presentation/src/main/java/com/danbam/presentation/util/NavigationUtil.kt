package com.danbam.presentation.util

sealed class AppNavigationItem(val route: String) {
    object Login : AppNavigationItem("login")

    object FindId : AppNavigationItem("findId")

    object FindPassword : AppNavigationItem("findPassword")

    object Main : AppNavigationItem("main")
}