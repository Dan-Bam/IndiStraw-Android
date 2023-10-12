package com.danbam.indistraw.core.data.local.preference

import android.content.SharedPreferences
import javax.inject.Inject

class SystemPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SystemPreference {
    override suspend fun saveLanguage(language: String) =
        saveStringPreference(LANGUAGE, language)

    override suspend fun fetchLanguage(): String =
        fetchStringPreference(LANGUAGE)

    private fun fetchStringPreference(key: String): String =
        sharedPreferences.getString(key, null) ?: ""

    private fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }

    companion object Key {
        const val LANGUAGE = "LANGUAGE"
    }
}