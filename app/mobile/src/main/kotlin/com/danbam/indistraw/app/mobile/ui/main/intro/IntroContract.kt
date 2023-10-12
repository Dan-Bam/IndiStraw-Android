package com.danbam.indistraw.app.mobile.ui.main.intro

data class IntroState(
    val isNeedLogin: Boolean = false,
    val systemLanguage: String? = null
)

sealed class IntroSideEffect {
    object SuccessLogin : IntroSideEffect()
}