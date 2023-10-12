package com.danbam.indistraw.core.domain.usecase.auth

import com.danbam.indistraw.core.domain.repository.AuthRepository
import javax.inject.Inject

class SendCertificateNumberUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(phoneNumber: String) = runCatching {
        authRepository.sendCertificateNumber(phoneNumber = phoneNumber)
    }
}