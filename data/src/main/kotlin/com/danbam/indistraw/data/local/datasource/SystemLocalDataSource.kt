package com.danbam.indistraw.data.local.datasource

interface SystemLocalDataSource {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String
}