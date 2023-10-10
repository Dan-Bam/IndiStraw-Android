package com.danbam.domain.usecase.auth

import com.danbam.domain.repository.AuthRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke() = runCatching {
        authRepository.clearToken()
    }
}