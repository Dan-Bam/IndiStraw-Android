package com.danbam.domain.usecase.auth

import com.danbam.domain.param.SignUpParam
import com.danbam.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(signUpParam: SignUpParam) = runCatching {
        authRepository.signup(signUpParam = signUpParam)
    }
}