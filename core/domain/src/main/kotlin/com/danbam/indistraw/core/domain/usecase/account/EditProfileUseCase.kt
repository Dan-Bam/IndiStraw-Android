package com.danbam.indistraw.core.domain.usecase.account

import com.danbam.indistraw.core.domain.param.auth.EditProfileParam
import com.danbam.indistraw.core.domain.repository.AccountRepository
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(editProfileParam: EditProfileParam) = runCatching {
        accountRepository.editProfile(editProfileParam = editProfileParam)
    }
}