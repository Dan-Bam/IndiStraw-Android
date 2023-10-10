package com.danbam.domain.usecase.account

import com.danbam.domain.param.auth.ChangeAddressParam
import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class ChangeAddressUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(changeAddressParam: ChangeAddressParam) = runCatching {
        accountRepository.changeAddress(changeAddressParam = changeAddressParam)
    }
}