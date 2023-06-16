package com.danbam.presentation.ui.auth.find.find_password

sealed class FindPasswordSideEffect {
    object EmptyPasswordException : FindPasswordSideEffect()
    object EmptyRePasswordException : FindPasswordSideEffect()
    object DifferentPasswordException : FindPasswordSideEffect()
    object LengthPasswordException : FindPasswordSideEffect()
    object MatchPasswordException : FindPasswordSideEffect()
    object FailChangeException : FindPasswordSideEffect()
    object SuccessChange : FindPasswordSideEffect()
}