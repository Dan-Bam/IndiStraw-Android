package com.danbam.presentation.ui.main.intro

data class IntroState(
    val isNeedLogin: Boolean = false,
)

sealed class IntroSideEffect {
    object SuccessLogin : IntroSideEffect()
}