package com.danbam.indistraw.app.mobile.ui.profile.setting

sealed class SettingSideEffect {
    object SuccessLogout : SettingSideEffect()
    object SuccessWithdraw : SettingSideEffect()
}