package com.danbam.presentation.ui.certificate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.auth.CheckCertificateNumberUseCase
import com.danbam.domain.usecase.auth.CheckPhoneNumberUseCase
import com.danbam.domain.usecase.auth.SendCertificateNumberUseCase
import com.danbam.presentation.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CertificateViewModel @Inject constructor(
    private val checkPhoneNumberUseCase: CheckPhoneNumberUseCase,
    private val sendCertificateNumberUseCase: SendCertificateNumberUseCase,
    private val checkCertificateNumberUseCase: CheckCertificateNumberUseCase,
) : ContainerHost<CertificateState, CertificateSideEffect>, ViewModel() {
    override val container = container<CertificateState, CertificateSideEffect>(CertificateState())

    fun checkPhoneNumber(phoneNumber: String, isSignUp: Boolean) = intent {
        if (phoneNumber.isEmpty()) postSideEffect(CertificateSideEffect.EmptyPhoneNumberException)
        else if (!"^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$".toRegex()
                .matches(phoneNumber)
        ) postSideEffect(CertificateSideEffect.MatchPhoneNumberException)
        else {
            viewModelScope.launch {
                checkPhoneNumberUseCase(phoneNumber = phoneNumber).onFailure {
                    it.errorHandling(unknownAction = {}, conflictException = {
                        if (!isSignUp) sendCertificateNumber(phoneNumber = phoneNumber)
                        else postSideEffect(CertificateSideEffect.NotEnrollPhoneNumberException)
                    }, noContentException = {
                        if (isSignUp) sendCertificateNumber(phoneNumber = phoneNumber)
                        else postSideEffect(CertificateSideEffect.EnrollPhoneNumberException)
                    })
                }
            }
        }
    }

    private fun sendCertificateNumber(phoneNumber: String) = intent {
        viewModelScope.launch {
            sendCertificateNumberUseCase(phoneNumber = phoneNumber.replace("-", "")).onSuccess {
                postSideEffect(CertificateSideEffect.SuccessSend)
                reduce { state.copy(phoneNumber = phoneNumber) }
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }

    fun checkCertificateNumber(authCode: String) = intent {
        if (authCode.isEmpty()) postSideEffect(CertificateSideEffect.EmptyCertificateNumberException)
        else {
            viewModelScope.launch {
                checkCertificateNumberUseCase(
                    authCode = authCode.toInt(),
                    phoneNumber = state.phoneNumber
                ).onFailure {
                    it.errorHandling(unknownAction = {}, wrongDataException = {
                        postSideEffect(CertificateSideEffect.WrongCertificateNumberException)
                    }, noContentException = {
                        postSideEffect(CertificateSideEffect.SuccessCertificate)
                        reduce { state.copy(phoneNumber = "") }
                    })
                }
            }
        }
    }

    fun expiredCertificateNumber() = intent {
        postSideEffect(CertificateSideEffect.ExpiredCertificateNumberException)
    }
}