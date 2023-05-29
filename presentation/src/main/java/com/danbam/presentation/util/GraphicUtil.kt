package com.danbam.presentation.util

import android.content.Context
import kotlin.math.roundToInt

fun Int.toDp(context: Context) = (this / context.resources.displayMetrics.density).roundToInt()