package com.danbam.domain.repository

interface SystemRepository {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String
}