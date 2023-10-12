package com.danbam.indistraw.core.local.util

import android.content.SharedPreferences

fun SharedPreferences.saveString(key: String, value: String) =
    editPreference { it.putString(key, value) }

fun SharedPreferences.fetchString(key: String): String? =
    getString(key, null)

fun SharedPreferences.clearString(key: String) =
    editPreference { it.remove(key) }

private fun SharedPreferences.editPreference(edit: (SharedPreferences.Editor) -> Unit) =
    edit().let {
        edit(it)
        it.apply()
    }
