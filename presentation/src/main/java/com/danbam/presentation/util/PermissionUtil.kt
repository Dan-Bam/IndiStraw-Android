package com.danbam.presentation.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun checkAndRequestPermissions(
    context: Context,
    permissions: Array<String>,
): Boolean =
    permissions.all {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }