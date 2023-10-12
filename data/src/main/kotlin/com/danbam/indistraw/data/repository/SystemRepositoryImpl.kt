package com.danbam.indistraw.data.repository

import com.danbam.indistraw.data.local.datasource.SystemLocalDataSource
import com.danbam.indistraw.domain.repository.SystemRepository
import javax.inject.Inject

class SystemRepositoryImpl @Inject constructor(
    private val systemLocalDataSource: SystemLocalDataSource
) : SystemRepository {
    override suspend fun saveLanguage(language: String) =
        systemLocalDataSource.saveLanguage(language)

    override suspend fun fetchLanguage(): String =
        systemLocalDataSource.fetchLanguage()
}