package com.danbam.indistraw.core.data.util

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun File.toMultipartBody(): MultipartBody.Part =
    MultipartBody.Part.createFormData(
        name = "file",
        filename = this.name,
        body = this.asRequestBody("*/*".toMediaType())
    )