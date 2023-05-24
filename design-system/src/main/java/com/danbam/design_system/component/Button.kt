package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun IndiStrawButton(
    modifier: Modifier = Modifier
        .padding(horizontal = 33.dp)
        .fillMaxWidth()
        .background(
            color = IndiStrawTheme.colors.main,
            shape = IndiStrawTheme.shapes.defaultRounded
        ),
    text: String,
    onClick: () -> Unit,
) {
    ButtonMedium(
        modifier = modifier
            .indiStrawClickable(onClick, rippleColor = IndiStrawTheme.colors.background)
            .padding(vertical = 16.dp),
        text = text,
        textAlign = TextAlign.Center
    )
}