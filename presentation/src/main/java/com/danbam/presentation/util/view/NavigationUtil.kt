package com.danbam.presentation.util.view

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
fun NavController.popBackStack(keyboardController: SoftwareKeyboardController? = null) {
    keyboardController?.hide()
    this.popBackStack()
}