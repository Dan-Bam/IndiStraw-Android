package com.danbam.indistraw.feature.tv.setting

import com.danbam.indistraw.core.design_system.util.android.Language
import com.danbam.indistraw.core.domain.entity.auth.ProfileEntity

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