package com.danbam.indistraw.design_system.attribute

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class IndiStrawShape(
    val smallRounded: RoundedCornerShape,
    val defaultRounded: RoundedCornerShape,
    val bigRounded: RoundedCornerShape,
    val bottomSheet: RoundedCornerShape,
    val topDefaultRounded: RoundedCornerShape,
    val bottomDefaultRounded: RoundedCornerShape,
    val circle: RoundedCornerShape,
)

internal val LocalIndiStrawShapes = staticCompositionLocalOf {
    IndiStrawShape(
        smallRounded = RoundedCornerShape(ZeroCornerSize),
        defaultRounded = RoundedCornerShape(ZeroCornerSize),
        bigRounded = RoundedCornerShape(ZeroCornerSize),
        bottomSheet = RoundedCornerShape(ZeroCornerSize),
        topDefaultRounded = RoundedCornerShape(ZeroCornerSize),
        bottomDefaultRounded = RoundedCornerShape(ZeroCornerSize),
        circle = RoundedCornerShape(ZeroCornerSize)
    )
}

val defaultShape = IndiStrawShape(
    smallRounded = RoundedCornerShape(5.dp),
    defaultRounded = RoundedCornerShape(10.dp),
    bigRounded = RoundedCornerShape(20.dp),
    bottomSheet = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    topDefaultRounded = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    bottomDefaultRounded = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
    circle = RoundedCornerShape(percent = 100)
)