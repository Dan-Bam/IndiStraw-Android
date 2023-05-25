package com.danbam.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.util.indiStrawClickable
import kotlinx.coroutines.delay

const val RestTime = 301

@Composable
fun IndiStrawTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 25.dp)
        .background(
            color = IndiStrawTheme.colors.textBox,
            shape = IndiStrawTheme.shapes.defaultRounded
        ),
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    isTimer: Boolean = false,
    isToggleVisible: Boolean? = null,
    onToggleChange: () -> Unit = {},
) {
    var restTime by remember { mutableStateOf(RestTime) }
    if (isTimer) {
        LaunchedEffect(restTime) {
            if (restTime != 0) {
                delay(1_000L)
                restTime--
            }
        }
    }
    Row(
        modifier = modifier
            .padding(vertical = 21.dp, horizontal = 13.dp)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            cursorBrush = SolidColor(IndiStrawTheme.colors.exampleText),
            textStyle = IndiStrawTheme.typography.exampleTextMedium.copy(color = IndiStrawTheme.colors.exampleText),
            visualTransformation = if (isToggleVisible == null || isToggleVisible) VisualTransformation.None else PasswordVisualTransformation(),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.8F)
                ) {
                    it()
                    if (value.isEmpty()) {
                        ExampleTextMedium(text = hint, color = IndiStrawTheme.colors.exampleText)
                    }

                }
                if (isTimer) {
                    ExampleTextMedium(
                        text = "(${restTime / 60}:${"%02d".format(restTime % 60)})",
                    )
                }
                if (value.isNotEmpty() && isToggleVisible != null) {
                    val eyesIcon =
                        if (isToggleVisible) IndiStrawIcon.CloseEyes else IndiStrawIcon.OpenEyes
                    Image(
                        modifier = Modifier
                            .height(15.dp)
                            .indiStrawClickable(onToggleChange),
                        painter = painterResource(id = eyesIcon.drawableId),
                        contentDescription = eyesIcon.contentDescription
                    )
                }
            }
        }
    }
}

@Composable
fun IndiStrawSearchTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp)
        .background(
            color = IndiStrawTheme.colors.background,
            shape = IndiStrawTheme.shapes.smallRounded
        ),
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    onToggleChange: () -> Unit = {},
) {
    var restTime by remember { mutableStateOf(RestTime) }
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = IndiStrawTheme.colors.text,
                shape = IndiStrawTheme.shapes.smallRounded
            )
            .padding(vertical = 10.dp, horizontal = 11.dp)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            cursorBrush = SolidColor(IndiStrawTheme.colors.exampleText),
            textStyle = IndiStrawTheme.typography.exampleTextMedium.copy(color = IndiStrawTheme.colors.exampleText),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.9F)
                ) {
                    it()
                    if (value.isEmpty()) {
                        ExampleTextMedium(text = hint, color = IndiStrawTheme.colors.exampleText)
                    }
                }
                Image(
                    modifier = Modifier
                        .height(15.dp)
                        .indiStrawClickable(onToggleChange),
                    painter = painterResource(id = IndiStrawIcon.Search.drawableId),
                    contentDescription = IndiStrawIcon.Search.contentDescription
                )
            }
        }
    }
}