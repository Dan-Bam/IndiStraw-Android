package com.danbam.presentation.util

sealed class AppNavigationItem(val route: String) {
    object Intro : AppNavigationItem("intro")

    object Login : AppNavigationItem("login")

    object Certificate : AppNavigationItem("certificate")

    object FindId : AppNavigationItem("findId")

    object FindPassword : AppNavigationItem("findPassword")

    object Main : AppNavigationItem("main")
}

sealed class SignUpNavigationItem(val route: String) {
    object SetName : SignUpNavigationItem("setName")

    object SetProfile : SignUpNavigationItem("setProfile")

    object SetId : SignUpNavigationItem("setId")

    object SetPassword : SignUpNavigationItem("setPassword")
}

object DeepLinkKey {
    const val CERTIFICATE_TYPE = "certificateType"
}

object CertificateType {
    const val SIGN_UP = "signUp"
    const val FIND_ID = "findId"
    const val FIND_PASSWORD = "findPassword"
}
