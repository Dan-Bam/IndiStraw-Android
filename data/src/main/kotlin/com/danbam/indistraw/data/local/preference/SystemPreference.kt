package com.danbam.indistraw.data.local.preference

interface SystemPreference {
    suspend fun saveLanguage(language: String)

    suspend fun fetchLanguage(): String
}