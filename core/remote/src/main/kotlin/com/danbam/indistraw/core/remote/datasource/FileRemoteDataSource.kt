package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.response.file.FileResponse
import okhttp3.MultipartBody

interface FileRemoteDataSource {
    suspend fun sendFile(file: MultipartBody.Part): FileResponse
}