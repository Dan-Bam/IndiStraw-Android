package com.danbam.mobile.util.parser

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URLDecoder
import java.time.LocalDateTime

@SuppressLint("Range")
fun Uri.toFile(context: Context): File {
    val fileName = getFileName(context)
    val file = createTempFile(context, fileName)
    copyToFile(context, this, file)
    return File(file.absolutePath)
}

private fun Uri.getFileName(context: Context): String {
    val name = URLDecoder.decode(toString().split("/").last(), Charsets.UTF_8.name())
    val ext = context.contentResolver.getType(this)!!.split("/").last()
    return "$name.$ext"
}

private fun createTempFile(context: Context, fileName: String): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File(storageDir, fileName)
}

private fun copyToFile(context: Context, uri: Uri, file: File) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = FileOutputStream(file)
    val buffer = ByteArray(4 * 1024)
    while (true) {
        val byteCount = inputStream!!.read(buffer)
        if (byteCount < 0) break
        outputStream.write(buffer, 0, byteCount)
    }
    outputStream.flush()
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
