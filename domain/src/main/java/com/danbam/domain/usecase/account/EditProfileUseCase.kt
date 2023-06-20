package com.danbam.domain.usecase.account

import com.danbam.domain.param.EditProfileParam
import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(editProfileParam: EditProfileParam) = runCatching {
        accountRepository.editProfile(editProfileParam = editProfileParam)
    }
}