package com.danbam.indistraw.feature.tv.main.intro

data class IntroState(
    val currentLanguage: String? = null
)

sealed class IntroSideEffect {
    object LoginSuccess : IntroSideEffect()
    object LoginFail : IntroSideEffect()
}