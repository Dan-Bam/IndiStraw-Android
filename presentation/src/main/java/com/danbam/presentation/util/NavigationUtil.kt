package com.danbam.presentation.util

sealed class AppNavigationItem(val route: String) {
    object Intro : AppNavigationItem("intro")

    object Login : AppNavigationItem("login")

    object FindId : AppNavigationItem("findId")

    object FindPassword : AppNavigationItem("findPassword")

    object Main : AppNavigationItem("main")
}

sealed class SignUpNavigationItem(val route: String) {
    object SetProfile : SignUpNavigationItem("setProfile")

    object SetName : SignUpNavigationItem("setName")

    object SetPhoneNumber : SignUpNavigationItem("setPhoneNumber")

    object SetId : SignUpNavigationItem("setId")

    object SetPassword : SignUpNavigationItem("setPassword")
}
