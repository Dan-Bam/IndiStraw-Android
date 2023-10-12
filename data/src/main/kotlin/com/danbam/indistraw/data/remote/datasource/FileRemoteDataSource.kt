package com.danbam.indistraw.data.remote.datasource

import com.danbam.indistraw.data.remote.response.file.FileResponse
import okhttp3.MultipartBody

interface FileRemoteDataSource {
    suspend fun sendFile(file: MultipartBody.Part): FileResponse
}