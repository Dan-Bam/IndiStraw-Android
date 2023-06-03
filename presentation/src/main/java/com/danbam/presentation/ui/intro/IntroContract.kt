package com.danbam.presentation.ui.intro

data class IntroState(
    val isNeedLogin: Boolean = false,
)

sealed class IntroSideEffect {
    object SuccessLogin : IntroSideEffect()
}