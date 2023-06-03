package com.danbam.data.util

import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.default(): LocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))