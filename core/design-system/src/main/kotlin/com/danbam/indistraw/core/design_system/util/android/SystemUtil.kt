package com.danbam.indistraw.core.design_system.util.android

import android.content.Context
import android.content.res.Configuration
import com.danbam.indistraw.core.design_system.R
import java.util.Locale

sealed class Language(val type: String, val stringId: Int) {
    companion object {
        fun toList() =
            listOf(English, Korean, Japanese, Spanish, Russian, German, Chinese, Vietnamese)
    }

    object English : Language("en", R.string.english)
    object Korean : Language("kr", R.string.korean)
    object Japanese : Language("ja", R.string.japanese)
    object Spanish : Language("es", R.string.spanish)
    object Chinese : Language("zh", R.string.chinese)
    object German : Language("de", R.string.german)
    object Vietnamese : Language("vi", R.string.vietnamese)
    object Russian : Language("ru", R.string.russian)
}

fun Language.changeLanguage(context: Context) {
    val locale = Locale(type)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}