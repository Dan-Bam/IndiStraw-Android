package com.danbam.indistraw.core.domain.usecase.auth

import com.danbam.indistraw.core.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke() = runCatching {
        authRepository.logout()
    }
}