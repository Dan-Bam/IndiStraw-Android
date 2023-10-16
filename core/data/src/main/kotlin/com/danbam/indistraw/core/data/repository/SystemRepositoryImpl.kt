package com.danbam.indistraw.core.data.repository

import com.danbam.indistraw.core.local.datasource.SystemLocalDataSource
import com.danbam.indistraw.core.domain.repository.SystemRepository
import javax.inject.Inject

class SystemRepositoryImpl @Inject constructor(
    private val systemLocalDataSource: com.danbam.indistraw.core.local.datasource.SystemLocalDataSource
) : SystemRepository {
    override suspend fun saveLanguage(language: String) =
        systemLocalDataSource.saveLanguage(language)

    override suspend fun fetchLanguage(): String =
        systemLocalDataSource.fetchLanguage()
}