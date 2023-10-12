package com.danbam.indistraw.mobile.ui.auth.certificate

data class CertificateState(
    val phoneNumber: String = "",
)

sealed class CertificateSideEffect {
    object EmptyPhoneNumberException : CertificateSideEffect()
    object MatchPhoneNumberException : CertificateSideEffect()
    object EnrollPhoneNumberException : CertificateSideEffect()
    object NotEnrollPhoneNumberException : CertificateSideEffect()
    object TooManyRequestPhoneNumberException : CertificateSideEffect()
    object EmptyCertificateNumberException : CertificateSideEffect()
    object WrongCertificateNumberException : CertificateSideEffect()
    object ExpiredCertificateNumberException : CertificateSideEffect()
    object TooManyRequestCertificateNumberException : CertificateSideEffect()
    object SuccessCertificate : CertificateSideEffect()
    object SuccessChangePhoneNumber : CertificateSideEffect()
}