package com.danbam.indistraw.domain.usecase.account

import com.danbam.indistraw.domain.param.auth.ChangeAddressParam
import com.danbam.indistraw.domain.repository.AccountRepository
import javax.inject.Inject

class ChangeAddressUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(changeAddressParam: ChangeAddressParam) = runCatching {
        accountRepository.changeAddress(changeAddressParam = changeAddressParam)
    }
}