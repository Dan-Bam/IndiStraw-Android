package com.danbam.presentation.ui.certificate

data class CertificateState(
    val phoneNumber: String = "",
)

sealed class CertificateSideEffect {
    object EmptyPhoneNumberException : CertificateSideEffect()
    object MatchPhoneNumberException : CertificateSideEffect()
    object EnrollPhoneNumberException : CertificateSideEffect()
    object NotEnrollPhoneNumberException : CertificateSideEffect()
    object EmptyCertificateNumberException : CertificateSideEffect()
    object WrongCertificateNumberException : CertificateSideEffect()
    object ExpiredCertificateNumberException : CertificateSideEffect()
    object SuccessCertificate : CertificateSideEffect()
}