package com.danbam.data.local.datasource

interface SystemLocalDataSource {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String
}