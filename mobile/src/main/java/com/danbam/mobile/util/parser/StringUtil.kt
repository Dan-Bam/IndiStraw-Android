package com.danbam.mobile.util.parser

import java.text.DecimalFormat

fun String.isPassword(): Boolean =
    "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#\$%^&*?~])[0-9a-zA-Z!@#\$%^&*?~]+\$".toRegex()
        .matches(this) && this.length in (5..20)

fun String.isPhoneNumber(): Boolean =
    "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})\$".toRegex().matches(this)

fun String.isId(): Boolean = this.length in (6..15)

fun String.toPhoneNumber() = this.replace("-", "")

fun Float.toMoney(): String = DecimalFormat("#,###").format(this)