package com.danbam.indistraw.core.domain.usecase.auth

import com.danbam.indistraw.core.param.auth.SignUpParam
import com.danbam.indistraw.core.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(signUpParam: SignUpParam) = runCatching {
        authRepository.signup(signUpParam = signUpParam)
    }
}