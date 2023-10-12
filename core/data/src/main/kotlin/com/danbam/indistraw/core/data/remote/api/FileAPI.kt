package com.danbam.indistraw.core.data.remote.api

import com.danbam.indistraw.core.data.remote.response.file.FileResponse
import com.danbam.indistraw.core.data.remote.util.EndPoint
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