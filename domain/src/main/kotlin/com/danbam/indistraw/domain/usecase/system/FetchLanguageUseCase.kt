package com.danbam.indistraw.domain.usecase.system

import com.danbam.indistraw.domain.repository.SystemRepository
import javax.inject.Inject

class FetchLanguageUseCase @Inject constructor(
    private val systemRepository: SystemRepository
) {
    suspend operator fun invoke() = runCatching {
        systemRepository.fetchLanguage()
    }
}