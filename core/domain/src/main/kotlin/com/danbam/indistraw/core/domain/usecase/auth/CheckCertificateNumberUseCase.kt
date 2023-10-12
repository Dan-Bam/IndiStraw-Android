package com.danbam.indistraw.core.domain.usecase.auth

import com.danbam.indistraw.core.domain.repository.AuthRepository
import javax.inject.Inject

class CheckCertificateNumberUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(authCode: Int, phoneNumber: String) = runCatching {
        authRepository.checkCertificateNumber(authCode = authCode, phoneNumber = phoneNumber)
    }
}