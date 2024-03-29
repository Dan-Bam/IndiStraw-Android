package com.danbam.indistraw.feature.mobile.auth.certificate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.ui.handling.errorHandling
import com.danbam.indistraw.core.design_system.util.internal.isPhoneNumber
import com.danbam.indistraw.core.domain.usecase.account.ChangePhoneNumberUseCase
import com.danbam.indistraw.core.domain.usecase.auth.CheckCertificateNumberUseCase
import com.danbam.indistraw.core.domain.usecase.auth.CheckPhoneNumberUseCase
import com.danbam.indistraw.core.domain.usecase.auth.SendCertificateNumberUseCase
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
    private val changePhoneNumberUseCase: ChangePhoneNumberUseCase,
) : ContainerHost<CertificateState, CertificateSideEffect>, ViewModel() {
    override val container = container<CertificateState, CertificateSideEffect>(CertificateState())

    fun clearPhoneNumber() = intent {
        reduce { state.copy(phoneNumber = "") }
    }

    fun checkPhoneNumber(phoneNumber: String, type: String) = intent {
        if (phoneNumber.isEmpty()) postSideEffect(CertificateSideEffect.EmptyPhoneNumberException)
        else if (!phoneNumber.isPhoneNumber()) postSideEffect(CertificateSideEffect.MatchPhoneNumberException)
        else {
            viewModelScope.launch {
                checkPhoneNumberUseCase(phoneNumber = phoneNumber, type = type).onSuccess {
                    sendCertificateNumber(phoneNumber = phoneNumber)
                }.onFailure {
                    it.errorHandling(unknownAction = {}, notFoundException = {
                        postSideEffect(CertificateSideEffect.NotEnrollPhoneNumberException)
                    }, conflictException = {
                        postSideEffect(CertificateSideEffect.EnrollPhoneNumberException)
                    }, tooManyRequestException = {
                        postSideEffect(CertificateSideEffect.TooManyRequestPhoneNumberException)
                    })
                }
            }
        }
    }

    fun sendCertificateNumber(phoneNumber: String) = intent {
        viewModelScope.launch {
            sendCertificateNumberUseCase(phoneNumber = phoneNumber).onSuccess {
                reduce { state.copy(phoneNumber = phoneNumber) }
            }.onFailure {
                it.errorHandling(unknownAction = {}, tooManyRequestException = {
                    postSideEffect(CertificateSideEffect.TooManyRequestCertificateNumberException)
                })
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
                ).onSuccess {
                    postSideEffect(CertificateSideEffect.SuccessCertificate)
                }.onFailure {
                    it.errorHandling(unknownAction = {}, wrongDataException = {
                        postSideEffect(CertificateSideEffect.WrongCertificateNumberException)
                    })
                }
            }
        }
    }

    fun expiredCertificateNumber() = intent {
        postSideEffect(CertificateSideEffect.ExpiredCertificateNumberException)
    }

    fun changePhoneNumber(phoneNumber: String) = intent {
        viewModelScope.launch {
            changePhoneNumberUseCase(phoneNumber = phoneNumber).onSuccess {
                postSideEffect(CertificateSideEffect.SuccessChangePhoneNumber)
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }
}