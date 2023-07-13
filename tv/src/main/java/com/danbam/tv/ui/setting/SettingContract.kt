package com.danbam.tv.ui.setting

import com.danbam.design_system.util.Language

data class SettingState(
    val currentLanguage: Language = Language.Korean
)

sealed class SettingSideEffect {
    object SuccessLogout : SettingSideEffect()
    object SuccessWithdraw : SettingSideEffect()
}