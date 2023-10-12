package com.danbam.indistraw.mobile.util.parser

import android.content.Context
import kotlin.math.roundToInt

fun Int.toDp(context: Context) = (this / context.resources.displayMetrics.density).roundToInt()