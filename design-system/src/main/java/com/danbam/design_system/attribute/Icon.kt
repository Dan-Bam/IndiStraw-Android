package com.danbam.design_system.attribute

import androidx.annotation.DrawableRes
import javax.annotation.concurrent.Immutable

@Immutable
class Icon private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {

    }
}