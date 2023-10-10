package com.danbam.mobile.ui.profile.setting

sealed class SettingSideEffect {
    object SuccessLogout : SettingSideEffect()
    object SuccessWithdraw : SettingSideEffect()
}