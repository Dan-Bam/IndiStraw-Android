package com.danbam.domain.usecase.system

import com.danbam.domain.repository.SystemRepository
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(
    private val systemRepository: SystemRepository
) {
    suspend operator fun invoke(language: String) = runCatching {
        systemRepository.saveLanguage(language = language)
    }
}