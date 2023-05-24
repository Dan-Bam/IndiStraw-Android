package com.danbam.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
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

@Composable
fun IndiStrawTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp)
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
    isToggleVisible: Boolean? = null,
    onToggleChange: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(vertical = 21.dp, horizontal = 13.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(if (isToggleVisible != null) 0.9F else 1F),
            value = value,
            onValueChange = onValueChange,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            cursorBrush = SolidColor(IndiStrawTheme.colors.exampleText),
            textStyle = IndiStrawTheme.typography.exampleTextMedium.copy(color = IndiStrawTheme.colors.exampleText),
            visualTransformation = if (isToggleVisible == null || isToggleVisible) VisualTransformation.None else PasswordVisualTransformation(),
        ) {
            it()
            if (value.isEmpty()) {
                Box {
                    ExampleTextMedium(text = hint, color = IndiStrawTheme.colors.exampleText)
                }
            }
        }
        if (value.isNotEmpty() && isToggleVisible != null) {
            val eyesIcon = if (isToggleVisible) IndiStrawIcon.CloseEyes else IndiStrawIcon.OpenEyes
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