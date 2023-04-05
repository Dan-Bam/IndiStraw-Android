package com.danbam.design_system.attribute

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class IndiStrawShape(
    val smallRoundedShape: RoundedCornerShape,
    val bigRoundedShape: RoundedCornerShape,
)

internal val LocalIndiStrawShapes = staticCompositionLocalOf {
    IndiStrawShape(
        smallRoundedShape = RoundedCornerShape(ZeroCornerSize),
        bigRoundedShape = RoundedCornerShape(ZeroCornerSize)
    )
}

val defaultSahpe = IndiStrawShape(
    smallRoundedShape = RoundedCornerShape(10.dp),
    bigRoundedShape = RoundedCornerShape(20.dp)
)