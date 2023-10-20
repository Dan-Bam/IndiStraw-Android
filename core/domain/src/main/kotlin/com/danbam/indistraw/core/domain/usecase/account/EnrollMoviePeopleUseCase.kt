package com.danbam.indistraw.core.domain.usecase.account

import com.danbam.indistraw.core.domain.repository.AccountRepository
import javax.inject.Inject

class EnrollMoviePeopleUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(peopleType: String, actorIdx: Long) = runCatching {
        accountRepository.enrollMoviePeople(peopleType = peopleType, actorIdx = actorIdx)
    }
}