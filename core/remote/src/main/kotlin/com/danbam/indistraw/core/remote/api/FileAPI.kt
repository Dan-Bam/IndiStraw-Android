package com.danbam.indistraw.core.remote.api

import com.danbam.indistraw.core.remote.response.file.FileResponse
import com.danbam.indistraw.core.remote.util.EndPoint
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileAPI {
    @Multipart
    @POST("${EndPoint.FILE}/")
    suspend fun sendFile(
        @Part file: MultipartBody.Part,
    ): FileResponse
}