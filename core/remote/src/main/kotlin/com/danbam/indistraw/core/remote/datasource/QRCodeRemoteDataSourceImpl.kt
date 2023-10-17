package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.BuildConfig
import com.danbam.indistraw.core.remote.api.QRCodeAPI
import com.danbam.indistraw.core.remote.request.auth.CheckQRCodeRequest
import com.danbam.indistraw.core.remote.response.auth.GetQRCodeResponse
import com.danbam.indistraw.core.remote.response.auth.LoginResponse
import com.danbam.indistraw.core.remote.util.EndPoint
import com.danbam.indistraw.core.remote.util.errorHandling
import com.danbam.indistraw.core.remote.util.indiStrawApiCall
import com.google.gson.Gson
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class QRCodeRemoteDataSourceImpl @Inject constructor(
    private val qrCodeAPI: QRCodeAPI
) : QRCodeRemoteDataSource {
    override suspend fun getQRCode(): GetQRCodeResponse =
        indiStrawApiCall {
            qrCodeAPI.getQRCode()
        }

    override suspend fun checkQRCode(uuid: UUID) =
        indiStrawApiCall {
            qrCodeAPI.checkQRCode(
                checkQRCodeRequest = CheckQRCodeRequest(
                    uuid = uuid
                )
            ).errorHandling()
        }

    override suspend fun connectQRCode(uuid: UUID, onSuccess: (LoginResponse) -> Unit) {
        val eventSourceListener = object : EventSourceListener() {
            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                if (type == "TOKEN") {
                    runCatching {
                        Gson().fromJson(data, LoginResponse::class.java)
                    }.onSuccess {
                        onSuccess(it)
                    }
                }
            }
        }
        val client = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()
        val request = Request.Builder()
            .url("${BuildConfig.BASE_URL}${EndPoint.QRCODE}/connect/${uuid}")
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        EventSources.createFactory(client)
            .newEventSource(request = request, listener = eventSourceListener)
    }
}