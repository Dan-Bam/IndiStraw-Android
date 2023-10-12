package com.danbam.indistraw.core.design_system.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme

sealed class MakeFundingProgress(val progress: Int) {
    object WriteIntroduce : MakeFundingProgress(0)
    object WriteTarget : MakeFundingProgress(1)
    object AddReward : MakeFundingProgress(2)
    object WriteReward : MakeFundingProgress(2)
    object WriteAccount : MakeFundingProgress(3)
}

@Composable
fun IndiStrawProgress(
    modifier: Modifier = Modifier,
    currentProgress: Double,
    enableText: Boolean = true
) {
    val progressAnimation by animateFloatAsState(targetValue = currentProgress.toFloat() / 100F)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .weight(1F)
                .height(16.dp)
                .clip(IndiStrawTheme.shapes.defaultRounded),
            color = IndiStrawTheme.colors.main,
            backgroundColor = IndiStrawTheme.colors.darkGray3,
            progress = progressAnimation
        )
        if (enableText) {
            Spacer(modifier = Modifier.width(4.dp))
            ExampleTextMedium(text = "${"%.1f".format(currentProgress)}%", fontSize = 14)
        }
    }
}

@Composable
fun IndiStrawMakeProgress(
    modifier: Modifier = Modifier,
    position: MakeFundingProgress
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(4) {
            MakeProgressDot(currentPosition = position, position = it)
            if (it < 3) {
                Spacer(
                    modifier = Modifier
                        .weight(1F)
                        .height(1.dp)
                        .padding(horizontal = 4.dp)
                        .background(if (position.progress > it) IndiStrawTheme.colors.main else IndiStrawTheme.colors.gray3)
                )
            }
        }
    }
}

@Composable
private fun MakeProgressDot(
    currentPosition: MakeFundingProgress,
    position: Int,
) {
    Box(
        modifier = Modifier
            .size(if (currentPosition.progress == position) 20.dp else 15.dp)
            .background(
                color = if (currentPosition.progress >= position) IndiStrawTheme.colors.main else Color.Transparent,
                shape = IndiStrawTheme.shapes.circle
            )
            .border(
                width = 1.dp,
                color = if (currentPosition.progress < position) IndiStrawTheme.colors.darkGray else Color.Transparent,
                shape = IndiStrawTheme.shapes.circle
            )
    )
}