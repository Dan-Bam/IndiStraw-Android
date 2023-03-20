package com.danbam.indi_straw.ui.theme

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