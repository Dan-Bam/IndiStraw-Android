package com.danbam.indistraw.feature.mobile.profile.setting

sealed class SettingSideEffect {
    object SuccessLogout : SettingSideEffect()
    object SuccessWithdraw : SettingSideEffect()
}