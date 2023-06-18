package com.danbam.domain.usecase.auth

import com.danbam.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke() = runCatching {
        authRepository.logout()
    }
}