package com.danbam.indistraw.core.design_system.util.internal

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

fun String.isPassword(): Boolean =
    "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#\$%^&*?~])[0-9a-zA-Z!@#\$%^&*?~]+\$".toRegex()
        .matches(this) && this.length in (5..20)

fun String.isPhoneNumber(): Boolean =
    "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})\$".toRegex().matches(this)

fun String.isId(): Boolean = this.length in (6..15)

fun String.toPhoneNumber() = this.replace("-", "")

fun Float.toCommaString(): String = DecimalFormat("#,###").format(this)