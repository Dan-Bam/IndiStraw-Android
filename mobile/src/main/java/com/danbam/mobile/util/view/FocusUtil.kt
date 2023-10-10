package com.danbam.mobile.util.view

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
fun FocusRequester.requestFocus(keyboardController: SoftwareKeyboardController? = null) {
    keyboardController?.show()
    this.requestFocus()
}