package com.danbam.indistraw.core.data.local.datasource

import com.danbam.indistraw.core.data.local.preference.SystemPreference
import javax.inject.Inject

class SystemLocalDataSourceImpl @Inject constructor(
    private val systemPreference: SystemPreference
) : SystemLocalDataSource {
    override suspend fun saveLanguage(language: String) =
        systemPreference.saveLanguage(language)

    override suspend fun fetchLanguage(): String =
        systemPreference.fetchLanguage()
}