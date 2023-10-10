package com.danbam.tv.util.parser

fun String.isPassword(): Boolean =
    "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#\$%^&*?~])[0-9a-zA-Z!@#\$%^&*?~]+\$".toRegex()
        .matches(this) && this.length in (5..20)

fun String.isId(): Boolean = this.length in (6..15)