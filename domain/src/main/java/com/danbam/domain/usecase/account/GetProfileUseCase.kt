package com.danbam.domain.usecase.account

import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke() = runCatching {
        accountRepository.getProfile()
    }
}