package com.danbam.domain.usecase.auth

import com.danbam.domain.param.auth.LoginParam
import com.danbam.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(loginParam: LoginParam) = runCatching {
        authRepository.login(loginParam = loginParam)
    }
}