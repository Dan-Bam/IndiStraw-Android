package com.danbam.data.remote.api

import com.danbam.data.remote.request.auth.CheckQRCodeRequest
import com.danbam.data.remote.response.auth.GetQRCodeResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface QRCodeAPI {
    @POST(EndPoint.QRCODE)
    suspend fun getQRCode(): GetQRCodeResponse

    @POST("${EndPoint.QRCODE}/signin")
    suspend fun checkQRCode(
        @Body checkQRCodeRequest: CheckQRCodeRequest
    ): Response<Void?>
}