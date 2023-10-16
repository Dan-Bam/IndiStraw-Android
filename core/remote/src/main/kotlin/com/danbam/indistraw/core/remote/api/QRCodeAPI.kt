package com.danbam.indistraw.core.remote.api

import com.danbam.indistraw.core.remote.request.auth.CheckQRCodeRequest
import com.danbam.indistraw.core.remote.response.auth.GetQRCodeResponse
import com.danbam.indistraw.core.remote.util.EndPoint
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