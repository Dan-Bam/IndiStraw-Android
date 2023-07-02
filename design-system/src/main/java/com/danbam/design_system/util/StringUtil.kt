package com.danbam.design_system.util

import java.text.DecimalFormat

fun Long.toMoney(): String = DecimalFormat("#,###").format(this)