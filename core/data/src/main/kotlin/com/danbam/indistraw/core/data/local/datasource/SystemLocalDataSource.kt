package com.danbam.indistraw.core.data.local.datasource

interface SystemLocalDataSource {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String
}