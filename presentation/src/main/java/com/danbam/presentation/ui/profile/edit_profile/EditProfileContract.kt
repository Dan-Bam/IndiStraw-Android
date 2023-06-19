package com.danbam.presentation.ui.profile.edit_profile

sealed class EditProfileSideEffect {
    data class SuccessUpload(val imageUrl: String) : EditProfileSideEffect()
}