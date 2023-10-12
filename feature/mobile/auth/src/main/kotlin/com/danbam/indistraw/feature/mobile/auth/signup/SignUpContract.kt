package com.danbam.indistraw.feature.mobile.auth.signup

data class SignUpState(
    val id: String = "",
    val password: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val profileUrl: String? = null,
)

sealed class SignUpSideEffect {
    object EmptyNameException : SignUpSideEffect()
    object EmptyIdException : SignUpSideEffect()
    object MatchIdException : SignUpSideEffect()
    object EnrollIdException : SignUpSideEffect()
    object EmptyPasswordException : SignUpSideEffect()
    object EmptyRePasswordException : SignUpSideEffect()
    object DifferentPasswordException : SignUpSideEffect()
    object LengthPasswordException : SignUpSideEffect()
    object MatchPasswordException : SignUpSideEffect()
    object Next : SignUpSideEffect()
    object SuccessSignUp : SignUpSideEffect()
    object FailSignUp : SignUpSideEffect()
    data class SuccessUpload(val imageUrl: String) : SignUpSideEffect()
}