package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.FileAPI
import com.danbam.data.remote.response.FileResponse
import com.danbam.data.remote.util.indiStrawApiCall
import okhttp3.MultipartBody
import javax.inject.Inject

class FileRemoteDataSourceImpl @Inject constructor(
    private val fileAPI: FileAPI,
) : FileRemoteDataSource {
    override suspend fun sendFile(file: MultipartBody.Part): FileResponse = indiStrawApiCall {
        fileAPI.sendFile(file = file)
    }
}