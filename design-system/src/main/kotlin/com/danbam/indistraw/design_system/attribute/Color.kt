package com.danbam.indistraw.design_system.attribute

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

@Immutable
data class IndiStrawColor(
    val main: Color,
    val black: Color,
    val skyBlue: Color,
    val white: Color,
    val darkGray: Color,
    val gray: Color,
    val red: Color,
    val darkGray2: Color,
    val gray2: Color,
    val lightBlack: Color,
    val gray3: Color,
    val lightGray: Color,
    val blue: Color,
    val darkGray3: Color,
    val navy: Color,
)

internal val LocalIndiStrawColors = staticCompositionLocalOf {
    IndiStrawColor(
        main = Color.Unspecified,
        black = Color.Unspecified,
        skyBlue = Color.Unspecified,
        white = Color.Unspecified,
        darkGray = Color.Unspecified,
        gray = Color.Unspecified,
        red = Color.Unspecified,
        darkGray2 = Color.Unspecified,
        gray2 = Color.Unspecified,
        lightBlack = Color.Unspecified,
        gray3 = Color.Unspecified,
        lightGray = Color.Unspecified,
        blue = Color.Unspecified,
        darkGray3 = Color.Unspecified,
        navy = Color.Unspecified
    )
}

val lightColor = IndiStrawColor(
    main = Color(0xFF7753DE),
    black = Color(0xFF000000),
    skyBlue = Color(0xFF81B7F8),
    white = Color(0xFFFFFFFF),
    darkGray = Color(0xFF4A4A4A),
    gray = Color(0xFFB5B5B5),
    red = Color(0xFFFF1717),
    darkGray2 = Color(0xFF3A3838),
    gray2 = Color(0xFF7C7C7C),
    lightBlack = Color(0xFF2F2F2F),
    gray3 = Color(0xFF7E7E7E),
    lightGray = Color(0xFFC9C7C7),
    blue = Color(0xFF0075FF),
    darkGray3 = Color(0xFF4D4D4D),
    navy = Color(0xFF2A2634)
)

val darkColor = IndiStrawColor(
    main = Color(0xFF7753DE),
    black = Color(0xFF000000),
    skyBlue = Color(0xFF81B7F8),
    white = Color(0xFFFFFFFF),
    darkGray = Color(0xFF4A4A4A),
    gray = Color(0xFFB5B5B5),
    red = Color(0xFFFF1717),
    darkGray2 = Color(0xFF3A3838),
    gray2 = Color(0xFF7C7C7C),
    lightBlack = Color(0xFF2F2F2F),
    gray3 = Color(0xFF7E7E7E),
    lightGray = Color(0xFFC9C7C7),
    blue = Color(0xFF0075FF),
    darkGray3 = Color(0xFF4D4D4D),
    navy = Color(0xFF2A2634)
)