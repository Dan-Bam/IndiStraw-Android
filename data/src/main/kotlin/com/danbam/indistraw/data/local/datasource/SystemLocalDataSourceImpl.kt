package com.danbam.indistraw.data.local.datasource

import com.danbam.indistraw.data.local.preference.SystemPreference
import javax.inject.Inject

class SystemLocalDataSourceImpl @Inject constructor(
    private val systemPreference: SystemPreference
) : SystemLocalDataSource {
    override suspend fun saveLanguage(language: String) =
        systemPreference.saveLanguage(language)

    override suspend fun fetchLanguage(): String =
        systemPreference.fetchLanguage()
}