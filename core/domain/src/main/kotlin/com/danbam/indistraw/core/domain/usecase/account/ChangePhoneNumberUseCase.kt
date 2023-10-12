package com.danbam.indistraw.core.domain.usecase.account

import com.danbam.indistraw.core.domain.repository.AccountRepository
import javax.inject.Inject

class ChangePhoneNumberUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(phoneNumber: String) = runCatching {
        accountRepository.changePhoneNumber(phoneNumber = phoneNumber)
    }
}