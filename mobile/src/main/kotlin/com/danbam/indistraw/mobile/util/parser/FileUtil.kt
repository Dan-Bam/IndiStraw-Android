package com.danbam.indistraw.mobile.util.parser

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import androidx.core.net.toFile
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.RuntimeException
import java.net.URLDecoder
import java.time.LocalDateTime

@SuppressLint("Range")
fun Uri.toFile(context: Context): File {
    return File(getFileName(context))
}

@SuppressLint("Range")
private fun Uri.getFileName(context: Context): String {
    val name = context.contentResolver.query(this, null, null, null)?.use {
        val nameIndex = it.getColumnIndex("_data")
        it.moveToFirst()
        it.getString(nameIndex)
    }
    return name ?: throw NotFoundFileException()
}

fun Bitmap.toFile(context: Context): File {
    val saveName = LocalDateTime.now().toString()
    val saveDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        .toString() + saveName
    val file = File(saveDir)
    if (!file.exists()) file.mkdirs()

    val fileName = "$saveName.jpg"
    val tempFile = File(saveDir, fileName)

    var out: OutputStream? = null
    try {
        if (tempFile.createNewFile()) {
            out = FileOutputStream(tempFile)
            compress(Bitmap.CompressFormat.JPEG, 100, out)
        }
    } finally {
        out?.close()
    }
    return tempFile
}

class NotFoundFileException : RuntimeException()
