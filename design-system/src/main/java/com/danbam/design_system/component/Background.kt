package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun IndiStrawColumnBackground(
    modifier: Modifier = Modifier,
    onClickAction: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(IndiStrawTheme.colors.background)
            .indiStrawClickable {
                onClickAction?.invoke()
            },
        content = content
    )
}

@Composable
fun IndiStrawBoxBackground(
    modifier: Modifier = Modifier,
    onClickAction: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(IndiStrawTheme.colors.background)
            .indiStrawClickable {
                onClickAction?.invoke()
            },
        content = content
    )
}