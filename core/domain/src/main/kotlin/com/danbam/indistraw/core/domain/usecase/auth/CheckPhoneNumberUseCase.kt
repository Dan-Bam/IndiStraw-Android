package com.danbam.indistraw.core.domain.usecase.auth

import com.danbam.indistraw.core.domain.repository.AuthRepository
import javax.inject.Inject

class CheckPhoneNumberUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(phoneNumber: String, type: String) = runCatching {
        authRepository.checkPhoneNumber(phoneNumber = phoneNumber, type = type)
    }
}