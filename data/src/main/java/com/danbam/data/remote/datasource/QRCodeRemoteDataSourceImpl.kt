package com.danbam.data.remote.datasource

import com.danbam.data.BuildConfig
import com.danbam.data.remote.api.QRCodeAPI
import com.danbam.data.remote.response.GetQRCodeResponse
import com.danbam.data.remote.util.EndPoint
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.io.IOException
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class QRCodeRemoteDataSourceImpl @Inject constructor(
    private val qrCodeAPI: QRCodeAPI
) : QRCodeRemoteDataSource {
    override suspend fun getQRCode(): GetQRCodeResponse = indiStrawApiCall {
        qrCodeAPI.getQRCode()
    }

    override suspend fun checkQRCode(uuid: UUID) = indiStrawApiCall {
        qrCodeAPI.checkQRCode(uuid = uuid).errorHandling()
    }

    override suspend fun connectQRCode(uuid: UUID, onSuccess: () -> Unit) {
        val eventSourceListener = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                super.onOpen(eventSource, response)
                println("안녕 열림")
            }

            override fun onClosed(eventSource: EventSource) {
                super.onClosed(eventSource)
                println("안녕 닫힘")
            }

            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                super.onEvent(eventSource, id, type, data)
                println("안녕 이벤트 받음")
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                super.onFailure(eventSource, t, response)
                println("안녕 에러 $t")
            }
        }
        val client = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
        val request = Request.Builder()
            .url("${BuildConfig.BASE_URL}${EndPoint.QRCODE}/connect/${uuid}")
            .header("Accept", "application/json; q=0.5")
            .addHeader("Accept", "text/event-stream")
            .build()
        EventSources.createFactory(client)
            .newEventSource(request = request, listener = eventSourceListener)
        client.newCall(request).enqueue(responseCallback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
            }
        })
    }
}