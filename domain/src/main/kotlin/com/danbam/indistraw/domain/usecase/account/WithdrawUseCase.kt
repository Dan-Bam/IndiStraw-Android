package com.danbam.indistraw.domain.usecase.account

import com.danbam.indistraw.domain.repository.AccountRepository
import javax.inject.Inject

class WithdrawUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke() = runCatching {
        accountRepository.withdraw()
    }
}