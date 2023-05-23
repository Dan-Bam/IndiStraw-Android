package com.danbam.design_system.attribute

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

@Immutable
data class IndiStrawColor(
    val button: Color,
    val background: Color,
    val more: Color,
    val text: Color,
    val profile: Color,
    val textBox: Color,
    val exampleText: Color,
    val error: Color,
    val check: Color,
)

internal val LocalIndiStrawColors = staticCompositionLocalOf {
    IndiStrawColor(
        button = Color.Unspecified,
        background = Color.Unspecified,
        more = Color.Unspecified,
        text = Color.Unspecified,
        profile = Color.Unspecified,
        textBox = Color.Unspecified,
        exampleText = Color.Unspecified,
        error = Color.Unspecified,
        check = Color.Unspecified
    )
}

val lightColor = IndiStrawColor(
    button = Color(0xFFEB5959),
    background = Color(0xFF000000),
    more = Color(0xFF81B7F8),
    text = Color(0xFFFFFFFF),
    profile = Color(0xFFCC7979),
    textBox = Color(0xFF4A4A4A),
    exampleText = Color(0xFFB5B5B5),
    error = Color(0xFFFF1717),
    check = Color(0xFF60EE5D)
)

val darkColor = IndiStrawColor(
    button = Color(0xFFEB5959),
    background = Color(0xFF000000),
    more = Color(0xFF81B7F8),
    text = Color(0xFFFFFFFF),
    profile = Color(0xFFCC7979),
    textBox = Color(0xFF4A4A4A),
    exampleText = Color(0xFFB5B5B5),
    error = Color(0xFFFF1717),
    check = Color(0xFF60EE5D)
)