package com.danbam.data.remote.datasource

import com.danbam.data.remote.response.GetQRCodeResponse

interface QRCodeRemoteDataSource {
    suspend fun getQRCode(): GetQRCodeResponse
}