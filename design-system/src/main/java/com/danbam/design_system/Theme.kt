package com.danbam.design_system

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.danbam.design_system.attribute.*

@Composable
fun IndiStrawTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) darkColor else lightColor

    CompositionLocalProvider(
        LocalIndiStrawColors provides colors,
        LocalIndiStrawShapes provides defaultSahpe,
        LocalIndiStrawTypography provides defaultTypography
    ) {
        MaterialTheme(
            content = content
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