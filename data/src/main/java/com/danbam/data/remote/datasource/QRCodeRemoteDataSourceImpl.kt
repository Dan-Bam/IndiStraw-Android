package com.danbam.data.remote.datasource

import com.danbam.data.BuildConfig
import com.danbam.data.remote.api.QRCodeAPI
import com.danbam.data.remote.request.CheckQRCodeRequest
import com.danbam.data.remote.response.GetQRCodeResponse
import com.danbam.data.remote.util.EndPoint
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
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
        qrCodeAPI.checkQRCode(checkQRCodeRequest = CheckQRCodeRequest(uuid = uuid)).errorHandling()
    }

    override suspend fun connectQRCode(uuid: UUID, onSuccess: () -> Unit) {
        val eventSourceListener = object : EventSourceListener() {
            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                super.onFailure(eventSource, t, response)
                println("안녕 $t")
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
                println("안녕 $id, $type, $data")
//                runCatching {
//                    Gson().fromJson(data, LoginResponse::class.java)
//                }.onSuccess {
//                    println("안녕 $it")
//                }.onFailure {
//                    println("안녕 $it")
//                }
            }
        }
        val client = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()
        val request = Request.Builder()
            .get()
            .url("${BuildConfig.BASE_URL}${EndPoint.QRCODE}/connect/${uuid}")
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        EventSources.createFactory(client)
            .newEventSource(request = request, listener = eventSourceListener)
    }
}