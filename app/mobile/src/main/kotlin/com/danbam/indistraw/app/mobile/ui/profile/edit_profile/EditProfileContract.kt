package com.danbam.indistraw.app.mobile.ui.profile.edit_profile

data class EditProfileState(
    val profileUrl: String? = null,
    val name: String = "",
    val phoneNumber: String = "",
    val address: String? = null,
)

sealed class EditProfileSideEffect {
    object EmptyNameException : EditProfileSideEffect()
    object SuccessUpload : EditProfileSideEffect()
    data class GetProfile(val name: String) : EditProfileSideEffect()
}