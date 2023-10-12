package com.danbam.indistraw.domain.usecase.auth

import com.danbam.indistraw.domain.param.auth.LoginParam
import com.danbam.indistraw.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(loginParam: LoginParam) = runCatching {
        authRepository.login(loginParam = loginParam)
    }
}