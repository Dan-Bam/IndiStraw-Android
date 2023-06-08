package com.danbam.presentation.ui.find.find_password

sealed class FindPasswordSideEffect {
    object EmptyException : FindPasswordSideEffect()
    object DifferentException : FindPasswordSideEffect()
    object LengthException : FindPasswordSideEffect()
    object MatchException : FindPasswordSideEffect()
    object FailChangeException : FindPasswordSideEffect()
    object SuccessChange : FindPasswordSideEffect()
}