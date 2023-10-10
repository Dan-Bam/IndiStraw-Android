package com.danbam.domain.usecase.account

import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class FindIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(phoneNumber: String) = runCatching {
        accountRepository.findId(phoneNumber = phoneNumber)
    }
}