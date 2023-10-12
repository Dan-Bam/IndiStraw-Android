package com.danbam.indistraw.core.design_system.util.internal

import android.content.Context
import kotlin.math.roundToInt

fun Int.toDp(context: Context) = (this / context.resources.displayMetrics.density).roundToInt()