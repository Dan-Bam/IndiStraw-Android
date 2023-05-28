package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.indiStrawClickable
import kotlinx.coroutines.delay

const val RestTime = 300

@Composable
fun IndiStrawTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
    readOnly: Boolean = false,
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
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .background(
                color = IndiStrawTheme.colors.textBox,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .padding(vertical = 21.dp, horizontal = 13.dp)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            cursorBrush = SolidColor(IndiStrawTheme.colors.exampleText),
            textStyle = IndiStrawTheme.typography.exampleTextMedium.copy(
                fontSize = 14.sp,
                color = IndiStrawTheme.colors.text
            ),
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
                        if (isToggleVisible) IndiStrawIconList.CloseEyes else IndiStrawIconList.OpenEyes
                    IndiStrawIcon(
                        modifier = Modifier
                            .height(15.dp)
                            .indiStrawClickable(onToggleChange),
                        icon = eyesIcon
                    )
                }
            }
        }
    }
}

@Composable
fun IndiStrawSearchTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    onToggleChange: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
            .background(
                color = IndiStrawTheme.colors.background,
                shape = IndiStrawTheme.shapes.smallRounded
            )
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
            textStyle = IndiStrawTheme.typography.exampleTextRegular,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.9F)
                ) {
                    it()
                    if (value.isEmpty()) {
                        ExampleTextRegular(text = hint, color = IndiStrawTheme.colors.exampleText)
                    }
                }
                IndiStrawIcon(
                    modifier = Modifier
                        .height(15.dp)
                        .indiStrawClickable(onToggleChange),
                    icon = IndiStrawIconList.Search
                )
            }
        }
    }
}