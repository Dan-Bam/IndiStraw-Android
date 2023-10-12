package com.danbam.indistraw.domain.usecase.account

import com.danbam.indistraw.domain.param.auth.ChangePasswordParam
import com.danbam.indistraw.domain.repository.AccountRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(changePasswordParam: ChangePasswordParam) = runCatching {
        accountRepository.changePassword(changePasswordParam = changePasswordParam)
    }
}