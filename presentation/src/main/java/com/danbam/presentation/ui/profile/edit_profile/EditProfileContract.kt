package com.danbam.presentation.ui.profile.edit_profile

data class EditProfileState(
    val profileUrl: String? = null,
    val name: String = "",
    val phoneNumber: String = "",
    val address: String? = null,
)

sealed class EditProfileSideEffect {
    object SuccessUpload : EditProfileSideEffect()
    data class GetProfile(val name: String) : EditProfileSideEffect()
}