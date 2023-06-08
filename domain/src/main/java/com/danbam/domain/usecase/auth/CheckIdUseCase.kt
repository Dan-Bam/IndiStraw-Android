package com.danbam.domain.usecase.auth

import com.danbam.domain.repository.AuthRepository
import javax.inject.Inject

class CheckIdUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(id: String) = runCatching {
        authRepository.checkId(id = id)
    }
}