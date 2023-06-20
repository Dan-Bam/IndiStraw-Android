package com.danbam.mobile.ui.main.intro

data class IntroState(
    val isNeedLogin: Boolean = false,
)

sealed class IntroSideEffect {
    object SuccessLogin : IntroSideEffect()
}