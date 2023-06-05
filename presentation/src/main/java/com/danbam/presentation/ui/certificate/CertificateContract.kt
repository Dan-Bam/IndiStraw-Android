package com.danbam.presentation.ui.certificate

data class CertificateState(
    val phoneNumber: String = "",
)

sealed class CertificateSideEffect {
    object SuccessSend : CertificateSideEffect()
    object Certificated : CertificateSideEffect()
}