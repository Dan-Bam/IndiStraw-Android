package com.danbam.design_system.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

inline fun Modifier.indiStrawClickable(
    crossinline onClick: () -> Unit,
    rippleColor: Color? = null,
): Modifier = composed {
    clickable(
        indication = rippleColor?.let {
            rememberRipple(
                color = it
            )
        },
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}