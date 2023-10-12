package com.danbam.indistraw.core.design_system.util.androidx

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

sealed class LaunchType {
    object Image : LaunchType()
    object File : LaunchType()
    object Video : LaunchType()
}

@Composable
fun rememberLauncher(
    selectFile: (Uri?) -> Unit
) =
    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectFile(result.data?.data)
        }
    }

fun ManagedActivityResultLauncher<Intent, ActivityResult>.typedLaunch(launchType: LaunchType) =
    launch(when (launchType) {
        is LaunchType.Image -> {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/*"
                action = Intent.ACTION_PICK
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            }
        }

        is LaunchType.File -> {
            Intent(
                Intent.ACTION_GET_CONTENT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ).apply {
                type = "application/*"
                action = Intent.ACTION_GET_CONTENT
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            }
        }

        is LaunchType.Video -> {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "video/*"
                action = Intent.ACTION_PICK
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            }
        }
    })