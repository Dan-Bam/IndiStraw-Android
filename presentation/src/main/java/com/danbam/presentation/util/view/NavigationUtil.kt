package com.danbam.presentation.util.view

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavController

sealed class AppNavigationItem(val route: String) {
    object Intro : AppNavigationItem("intro")
    object Login : AppNavigationItem("login")
    object Certificate : AppNavigationItem("certificate")
    object FindId : AppNavigationItem("findId")
    object FindPassword : AppNavigationItem("findPassword")
    object Main : AppNavigationItem("main")
    object Profile : AppNavigationItem("profile")
}

sealed class SignUpNavigationItem(val route: String) {
    object SetName : SignUpNavigationItem("setName")
    object SetProfile : SignUpNavigationItem("setProfile")
    object SetId : SignUpNavigationItem("setId")
    object SetPassword : SignUpNavigationItem("setPassword")
}

object DeepLinkKey {
    const val CERTIFICATE_TYPE = "certificateType"
    const val PHONE_NUMBER = "phoneNumber"
}

object CertificateType {
    const val SIGN_UP = "signUp"
    const val FIND_ID = "findId"
    const val FIND_PASSWORD = "findPassword"
}

@OptIn(ExperimentalComposeUiApi::class)
fun NavController.popBackStack(keyboardController: SoftwareKeyboardController? = null) {
    keyboardController?.hide()
    this.popBackStack()
}