package com.danbam.design_system.util

import java.text.DecimalFormat

fun Long.toCommaString(): String = DecimalFormat("#,###").format(this)