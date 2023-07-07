package com.danbam.data.remote.api

import com.danbam.data.remote.response.GetQRCodeResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.http.POST

interface QRCodeAPI {
    @POST(EndPoint.QRCODE)
    suspend fun getQRCode(): GetQRCodeResponse
}