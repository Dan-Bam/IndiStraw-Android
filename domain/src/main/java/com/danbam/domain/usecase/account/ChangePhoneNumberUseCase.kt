package com.danbam.domain.usecase.account

import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class ChangePhoneNumberUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(phoneNumber: String) = runCatching {
        accountRepository.changePhoneNumber(phoneNumber = phoneNumber)
    }
}