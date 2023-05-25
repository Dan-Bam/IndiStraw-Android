package com.danbam.design_system.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.danbam.design_system.IndiStrawTheme

@Composable
fun HeadLineBold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 24,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.headLineBold.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun ExampleTextMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 14,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.exampleTextMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun ExampleTextRegular(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.exampleTextRegular.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun FindPasswordMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 12,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.findPasswordMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun errorMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 12,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.errorMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun agreeMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 14,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.agreeMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TitleSemiBold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 14,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.titleSemiBold.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TitleRegular(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.titleRegular.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun PriceRegular(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 10,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.priceRegular.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun SuccessBold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.successBold.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun ButtonMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = IndiStrawTheme.colors.text,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 18,
) {
    Text(
        modifier = modifier,
        text = text,
        style = IndiStrawTheme.typography.buttonMedium.copy(fontSize = fontSize.sp),
        color = color,
        textAlign = textAlign
    )
}