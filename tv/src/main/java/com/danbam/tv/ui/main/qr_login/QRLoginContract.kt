package com.danbam.tv.ui.main.qr_login

import java.util.UUID

data class QRLoginState(
    val uuid: UUID? = null
)

sealed class QRLoginSideEffect {
    object SuccessLogin : QRLoginSideEffect()
}