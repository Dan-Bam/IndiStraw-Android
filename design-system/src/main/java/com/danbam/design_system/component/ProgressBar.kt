package com.danbam.design_system.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme

@Composable
fun IndiStrawProgress(
    modifier: Modifier = Modifier,
    currentProgress: Float,
) {
    val progressAnimation by animateFloatAsState(targetValue = currentProgress / 100)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.7F)
                .height(17.dp)
                .clip(IndiStrawTheme.shapes.defaultRounded),
            color = IndiStrawTheme.colors.main,
            backgroundColor = IndiStrawTheme.colors.darkGray3,
            progress = progressAnimation
        )
        Spacer(modifier = Modifier.width(5.45.dp))
        ExampleTextMedium(text = "$currentProgress%")
    }
}