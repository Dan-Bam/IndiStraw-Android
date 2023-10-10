package com.danbam.tv.ui.setting

import com.danbam.design_system.util.Language
import com.danbam.domain.entity.auth.ProfileEntity

data class SettingState(
    val currentLanguage: Language = Language.Korean,
    val profileEntity: ProfileEntity = ProfileEntity(
        id = "",
        name = "",
        phoneNumber = "",
        zipcode = null,
        address = null,
        profileUrl = null
    )
)

sealed class SettingSideEffect {
    object SuccessLogout : SettingSideEffect()
    object SuccessWithdraw : SettingSideEffect()
}