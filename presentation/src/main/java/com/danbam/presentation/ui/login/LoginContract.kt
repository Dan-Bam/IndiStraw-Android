package com.danbam.presentation.ui.login


sealed class LoginSideEffect {
    object LoginSuccess : LoginSideEffect()
    object IdEmpty : LoginSideEffect()
    object PasswordEmpty : LoginSideEffect()
    object WrongId : LoginSideEffect()
    object WrongPassword : LoginSideEffect()
    object UnKnownError : LoginSideEffect()
}