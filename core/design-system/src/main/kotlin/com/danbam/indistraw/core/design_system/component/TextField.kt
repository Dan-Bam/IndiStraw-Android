package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable

@Composable
fun IndiStrawTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    maxLines: Int = 1,
    readOnly: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordVisible: Boolean = false,
    tailingIcon: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(
                color = IndiStrawTheme.colors.darkGray,
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
            cursorBrush = SolidColor(IndiStrawTheme.colors.gray),
            textStyle = IndiStrawTheme.typography.exampleTextMedium.copy(
                fontSize = 14.sp,
                color = IndiStrawTheme.colors.white
            ),
            visualTransformation = if (!isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(if (tailingIcon == null) 1F else 0.8F)
                ) {
                    it()
                    if (value.isEmpty()) {
                        ExampleTextMedium(text = hint, color = IndiStrawTheme.colors.gray)
                    }

                }
                tailingIcon?.let { it() }
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
    imeAction: ImeAction = ImeAction.Search,
    keyboardType: KeyboardType = KeyboardType.Text,
    onSearch: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
            .background(
                color = IndiStrawTheme.colors.black,
                shape = IndiStrawTheme.shapes.smallRounded
            )
            .border(
                width = 1.dp,
                color = IndiStrawTheme.colors.white,
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
            keyboardActions = KeyboardActions(
                onSearch = { if (value.isNotEmpty()) onSearch() }
            ),
            cursorBrush = SolidColor(IndiStrawTheme.colors.gray),
            textStyle = IndiStrawTheme.typography.exampleTextMedium.copy(
                fontSize = 16.sp,
                color = IndiStrawTheme.colors.white
            ),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.9F)
                ) {
                    it()
                    if (value.isEmpty()) {
                        ExampleTextRegular(text = hint, color = IndiStrawTheme.colors.gray)
                    }
                }
                IndiStrawIcon(
                    modifier = Modifier
                        .size(15.dp)
                        .indiStrawClickable(onClick = { if (value.isNotEmpty()) onSearch() }),
                    icon = IndiStrawIconList.Search
                )
            }
        }
    }
}

@Composable
fun IndiStrawTvTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    var focused by remember { mutableStateOf(false) }
    BasicTextField(
        modifier = modifier
            .onFocusChanged {
                focused = it.hasFocus || it.isFocused
            }
            .background(
                color = if (focused) IndiStrawTheme.colors.navy else IndiStrawTheme.colors.darkGray3,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .border(
                width = 3.dp,
                color = if (focused) IndiStrawTheme.colors.main else Color.Transparent,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .fillMaxWidth(0.42F)
            .padding(horizontal = 20.dp, vertical = 15.dp),
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyboardActions,
        cursorBrush = SolidColor(IndiStrawTheme.colors.white),
        textStyle = IndiStrawTheme.typography.exampleTextRegular.copy(
            fontSize = 24.sp,
            color = IndiStrawTheme.colors.white
        ),
        visualTransformation = visualTransformation,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            it()
            if (value.isEmpty()) {
                ExampleTextRegular(text = hint, fontSize = 24)
            }

        }
    }
}