package com.danbam.presentation.ui.login


sealed class LoginSideEffect {
    object LoginSuccess : LoginSideEffect()
    object EmptyIdException : LoginSideEffect()
    object EmptyPasswordException : LoginSideEffect()
    object MatchIdException : LoginSideEffect()
    object MatchPasswordException : LoginSideEffect()
    object UnKnownException : LoginSideEffect()
}