package com.danbam.design_system.util

import android.content.Context
import android.content.res.Configuration
import com.danbam.design_system.R
import java.util.Locale

sealed class Language(val type: String, val stringId: Int) {
    companion object {
        fun toList() = listOf(English, Korean, Japanese, Spanish)
    }

    object English : Language("en", R.string.english)
    object Korean : Language("kr", R.string.korean)
    object Japanese : Language("ja", R.string.japanese)
    object Spanish : Language("es", R.string.spanish)
}

fun Language.changeLanguage(context: Context) {
    val locale = Locale(type)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}