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
    val headLineBold1: TextStyle,
    val headLineBold2: TextStyle,
    val headLineBold3: TextStyle,
    val exampleTextMedium: TextStyle,
    val exampleTextRegular: TextStyle,
    val findPasswordMedium: TextStyle,
    val errorMedium: TextStyle,
    val agreeMedium: TextStyle,
    val titleSemiBold1: TextStyle,
    val titleSemiBold2: TextStyle,
    val titleRegular1: TextStyle,
    val titleRegular2: TextStyle,
    val titleRegular3: TextStyle,
    val priceRegular: TextStyle,
    val successBold: TextStyle,
    val buttonMedium: TextStyle,
)

internal val LocalIndiStrawTypography = staticCompositionLocalOf {
    IndiStrawTypography(
        headLineBold1 = TextStyle.Default,
        headLineBold2 = TextStyle.Default,
        headLineBold3 = TextStyle.Default,
        exampleTextMedium = TextStyle.Default,
        exampleTextRegular = TextStyle.Default,
        findPasswordMedium = TextStyle.Default,
        errorMedium = TextStyle.Default,
        agreeMedium = TextStyle.Default,
        titleSemiBold1 = TextStyle.Default,
        titleSemiBold2 = TextStyle.Default,
        titleRegular1 = TextStyle.Default,
        titleRegular2 = TextStyle.Default,
        titleRegular3 = TextStyle.Default,
        priceRegular = TextStyle.Default,
        successBold = TextStyle.Default,
        buttonMedium = TextStyle.Default
    )
}

val defaultTypography = IndiStrawTypography(
    headLineBold1 = TextStyle(fontFamily = suit, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    headLineBold2 = TextStyle(fontFamily = suit, fontWeight = FontWeight.Bold, fontSize = 16.sp),
    headLineBold3 = TextStyle(fontFamily = suit, fontWeight = FontWeight.Bold, fontSize = 20.sp),
    exampleTextMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    exampleTextRegular = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    findPasswordMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    errorMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    agreeMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium, fontSize = 14.sp) ,
    titleSemiBold1 = TextStyle(fontFamily = suit, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
    titleSemiBold2 = TextStyle(fontFamily = suit, fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
    titleRegular1 = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    titleRegular2 = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    titleRegular3 = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    priceRegular = TextStyle(fontFamily = suit, fontWeight = FontWeight.Normal, fontSize = 10.sp),
    successBold = TextStyle(fontFamily = suit, fontWeight = FontWeight.Bold, fontSize = 16.sp),
    buttonMedium = TextStyle(fontFamily = suit, fontWeight = FontWeight.Medium, fontSize = 18.sp)
)