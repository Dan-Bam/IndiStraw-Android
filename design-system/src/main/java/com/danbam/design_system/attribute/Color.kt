package com.danbam.design_system.attribute

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

@Immutable
data class IndiStrawColor(
    val main: Color,
    val background: Color,
    val more: Color,
    val text: Color,
    val profile: Color,
    val textBox: Color,
    val exampleText: Color,
    val error: Color,
    val bottomSheet: Color,
    val line: Color,
)

internal val LocalIndiStrawColors = staticCompositionLocalOf {
    IndiStrawColor(
        main = Color.Unspecified,
        background = Color.Unspecified,
        more = Color.Unspecified,
        text = Color.Unspecified,
        profile = Color.Unspecified,
        textBox = Color.Unspecified,
        exampleText = Color.Unspecified,
        error = Color.Unspecified,
        bottomSheet = Color.Unspecified,
        line = Color.Unspecified,
    )
}

val lightColor = IndiStrawColor(
    main = Color(0xFF7753DE),
    background = Color(0xFF000000),
    more = Color(0xFF81B7F8),
    text = Color(0xFFFFFFFF),
    profile = Color(0xFFCC7979),
    textBox = Color(0xFF4A4A4A),
    exampleText = Color(0xFFB5B5B5),
    error = Color(0xFFFF1717),
    bottomSheet = Color(0xFF3A3838),
    line = Color(0xFF7C7C7C),
)

val darkColor = IndiStrawColor(
    main = Color(0xFF7753DE),
    background = Color(0xFF000000),
    more = Color(0xFF81B7F8),
    text = Color(0xFFFFFFFF),
    profile = Color(0xFFCC7979),
    textBox = Color(0xFF4A4A4A),
    exampleText = Color(0xFFB5B5B5),
    error = Color(0xFFFF1717),
    bottomSheet = Color(0xFF3A3838),
    line = Color(0xFF7C7C7C),
)