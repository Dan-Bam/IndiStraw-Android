package com.danbam.indistraw.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.danbam.indistraw.design_system.attribute.*

@Composable
fun IndiStrawTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) darkColor else lightColor

    CompositionLocalProvider(
        LocalIndiStrawColors provides colors,
        LocalIndiStrawShapes provides defaultShape,
        LocalIndiStrawTypography provides defaultTypography
    ) {
        MaterialTheme(
            content = {
                Box(
                    modifier = Modifier
                        .background(IndiStrawTheme.colors.black)
                        .fillMaxSize()
                ) {
                    content()
                }
            }
        )
    }
}

object IndiStrawTheme {
    val colors: IndiStrawColor
        @Composable
        get() = LocalIndiStrawColors.current

    val shapes: IndiStrawShape
        @Composable
        get() = LocalIndiStrawShapes.current

    val typography: IndiStrawTypography
        @Composable
        get() = LocalIndiStrawTypography.current
}