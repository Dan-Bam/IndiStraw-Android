package com.danbam.indistraw.core.design_system.util

import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

fun Long.toCommaString(): String = DecimalFormat("#,###").format(this)

fun Long.formatMinSec(): String = String.format(
    "%02d : %02d",
    TimeUnit.MILLISECONDS.toMinutes(this),
    TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(
        TimeUnit.MILLISECONDS.toMinutes(this)
    )
)