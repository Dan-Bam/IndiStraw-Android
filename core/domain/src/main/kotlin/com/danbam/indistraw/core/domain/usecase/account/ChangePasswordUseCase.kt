package com.danbam.indistraw.core.domain.usecase.account

import com.danbam.indistraw.core.param.auth.ChangePasswordParam
import com.danbam.indistraw.core.domain.repository.AccountRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(changePasswordParam: ChangePasswordParam) = runCatching {
        accountRepository.changePassword(changePasswordParam = changePasswordParam)
    }
}