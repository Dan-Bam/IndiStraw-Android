package com.danbam.indistraw.core.domain.usecase.account

import com.danbam.indistraw.core.domain.repository.AccountRepository
import javax.inject.Inject

class EnrollMoviePeople @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(actorType: String, actorIdx: Long) = runCatching {
        accountRepository.enrollMoviePeople(actorType = actorType, actorIdx = actorIdx)
    }
}