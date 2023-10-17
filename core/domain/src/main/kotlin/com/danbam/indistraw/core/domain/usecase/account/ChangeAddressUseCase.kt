package com.danbam.indistraw.core.domain.usecase.account

import com.danbam.indistraw.core.domain.param.auth.ChangeAddressParam
import com.danbam.indistraw.core.domain.repository.AccountRepository
import javax.inject.Inject

class ChangeAddressUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(changeAddressParam: ChangeAddressParam) = runCatching {
        accountRepository.changeAddress(changeAddressParam = changeAddressParam)
    }
}