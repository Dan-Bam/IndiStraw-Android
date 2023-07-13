package com.danbam.tv.ui.main.intro

data class IntroState(
    val currentLanguage: String? = null
)

sealed class IntroSideEffect {
    object LoginSuccess : IntroSideEffect()
    object LoginFail : IntroSideEffect()
}