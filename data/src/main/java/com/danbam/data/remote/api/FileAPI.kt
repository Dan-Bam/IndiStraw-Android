package com.danbam.data.remote.api

import com.danbam.data.remote.response.FileResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileAPI {
    @Multipart
    @POST("file/")
    suspend fun sendFile(
        @Part file: MultipartBody.Part,
    ): FileResponse
}