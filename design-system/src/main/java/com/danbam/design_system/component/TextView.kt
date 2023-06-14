package com.danbam.design_system.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.danbam.design_system.IndiStrawTheme

@Composable
fun HeadLineBold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 24,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.headLineBold.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun ExampleTextMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 14,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.exampleTextMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun ExampleTextRegular(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.exampleTextRegular.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun FindPasswordMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 12,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.findPasswordMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun errorMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 12,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.errorMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun agreeMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 14,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.agreeMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun TitleSemiBold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 14,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.titleSemiBold.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun TitleRegular(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.titleRegular.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun PriceRegular(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 10,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.priceRegular.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun SuccessBold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.successBold.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun ButtonMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 18,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.buttonMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun JoinBold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.white,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 12,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.joinBold.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}