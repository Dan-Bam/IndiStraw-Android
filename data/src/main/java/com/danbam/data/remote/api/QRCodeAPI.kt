package com.danbam.data.remote.api

import com.danbam.data.remote.response.GetQRCodeResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.HEAD
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface QRCodeAPI {
    @POST(EndPoint.QRCODE)
    suspend fun getQRCode(): GetQRCodeResponse

    @HEAD("${EndPoint.QRCODE}/check/{uuid}")
    suspend fun checkQRCode(
        @Path("uuid") uuid: UUID
    ): Response<Void?>
}