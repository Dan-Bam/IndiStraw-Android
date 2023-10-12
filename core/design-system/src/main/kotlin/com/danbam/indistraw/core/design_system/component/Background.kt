package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndiStrawColumnBackground(
    modifier: Modifier = Modifier,
    onClickAction: (() -> Unit)? = null,
    scrollEnabled: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        val scrollState = rememberScrollState()
        val columnModifier = if (scrollEnabled) {
            modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        } else {
            modifier.fillMaxSize()
        }
        Column(
            modifier = columnModifier
                .background(IndiStrawTheme.colors.black)
                .indiStrawClickable {
                    onClickAction?.invoke()
                },
            content = content
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndiStrawBoxBackground(
    modifier: Modifier = Modifier,
    onClickAction: (() -> Unit)? = null,
    scrollEnabled: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        val scrollState = rememberScrollState()
        val boxModifier = if (scrollEnabled) {
            modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        } else {
            modifier.fillMaxSize()
        }
        Box(
            modifier = boxModifier
                .background(IndiStrawTheme.colors.black)
                .indiStrawClickable {
                    onClickAction?.invoke()
                },
            content = content
        )
    }
}

@Composable
fun IndiStrawTvBackground(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(IndiStrawTheme.colors.black),
        content = content
    )
}

