package com.danbam.indistraw.core.domain.usecase.account

import com.danbam.indistraw.core.domain.repository.AccountRepository
import javax.inject.Inject

class WithdrawUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke() = runCatching {
        accountRepository.withdraw()
    }
}