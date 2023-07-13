package com.danbam.data.repository

import com.danbam.data.local.datasource.SystemLocalDataSource
import com.danbam.domain.repository.SystemRepository
import javax.inject.Inject

class SystemRepositoryImpl @Inject constructor(
    private val systemLocalDataSource: SystemLocalDataSource
) : SystemRepository {
    override suspend fun saveLanguage(language: String) =
        systemLocalDataSource.saveLanguage(language)

    override suspend fun fetchLanguage(): String =
        systemLocalDataSource.fetchLanguage()
}