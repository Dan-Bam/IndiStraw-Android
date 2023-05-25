package com.danbam.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun IndiStrawCheckBox(
    modifier: Modifier = Modifier,
    isBorder: Boolean = false,
    isCheck: Boolean,
    onClick: () -> Unit,
) {
    val checkIcon = if (isCheck) IndiStrawIcon.Check else IndiStrawIcon.UnCheck
    Box(
        modifier = if (isBorder) Modifier
            .border(
                width = 2.dp,
                color = IndiStrawTheme.colors.exampleText,
                shape = IndiStrawTheme.shapes.smallRounded
            ) else Modifier
    ) {
        Image(
            modifier = modifier
                .padding(horizontal = 4.dp, vertical = 6.dp)
                .indiStrawClickable(onClick),
            painter = painterResource(id = checkIcon.drawableId),
            contentDescription = checkIcon.contentDescription
        )
    }
}