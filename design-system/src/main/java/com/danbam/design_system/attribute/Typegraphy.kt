package com.danbam.design_system.attribute

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.danbam.design_system.R

internal val suit = FontFamily(
    Font(R.font.suit_bold, FontWeight.Bold),
    Font(R.font.suit_semibold, FontWeight.SemiBold),
    Font(R.font.suit_medium, FontWeight.Medium),
    Font(R.font.suit_regular, FontWeight.Normal)
)

@Immutable
data class IndiStrawTypography(
    val headLineBold: TextStyle,
    val exampleTextMedium: TextStyle,
    val exampleTextRegular: TextStyle,
    val findPasswordMedium: TextStyle,
    val errorMedium: TextStyle,
    val agreeMedium: TextStyle,
    val titleSemiBold: TextStyle,
    val titleRegular: TextStyle,
    val priceRegular: TextStyle,
    val successBold: TextStyle,
    val buttonMedium: TextStyle,
    val joinBold: TextStyle,
    val dialogMedium: TextStyle,
)

internal val LocalIndiStrawTypography = staticCompositionLocalOf {
    IndiStrawTypography(
        headLineBold = TextStyle.Default,
        exampleTextMedium = TextStyle.Default,
        exampleTextRegular = TextStyle.Default,
        findPasswordMedium = TextStyle.Default,
        errorMedium = TextStyle.Default,
        agreeMedium = TextStyle.Default,
        titleSemiBold = TextStyle.Default,
        titleRegular = TextStyle.Default,
        priceRegular = TextStyle.Default,
        successBold = TextStyle.Default,
        buttonMedium = TextStyle.Default,
        joinBold = TextStyle.Default,
        dialogMedium = TextStyle.Default
    )
}

val defaultTypography = IndiStrawTypography(
    headLineBold = TextStyle(fontFamily = suit, fontWeight = FontWeight.Bold),
    exampleTextMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium),
    exampleTextRegular = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal),
    findPasswordMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium),
    errorMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium),
    agreeMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium),
    titleSemiBold = TextStyle(fontFamily = suit, fontWeight = FontWeight.SemiBold),
    titleRegular = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal),
    priceRegular = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal),
    successBold = TextStyle(fontFamily = suit, fontWeight = FontWeight.Bold),
    buttonMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium),
    joinBold = TextStyle(fontFamily = suit, fontWeight = FontWeight.Bold),
    dialogMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium)
)