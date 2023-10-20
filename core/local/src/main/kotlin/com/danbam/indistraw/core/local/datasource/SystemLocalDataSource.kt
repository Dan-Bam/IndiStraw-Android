package com.danbam.indistraw.core.local.datasource

interface SystemLocalDataSource {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String
}