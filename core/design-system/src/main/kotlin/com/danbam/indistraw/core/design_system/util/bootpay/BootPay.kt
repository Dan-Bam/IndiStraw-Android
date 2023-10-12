package com.danbam.indistraw.core.design_system.util.bootpay

import android.app.Activity
import android.content.Context
import com.danbam.indistraw.core.design_system.BuildConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.Payload

data class BootPayEvent(
    @SerializedName("event")
    val event: String,
    @SerializedName("data")
    val data: BootPayData,
) {
    data class BootPayData(
        @SerializedName("receipt_id")
        val receipt_id: String,
    )
}

fun bootPayPayload(title: String, price: Double, orderId: String, method: String): Payload {
    return Payload().setApplicationId(BuildConfig.PAY_KEY)
        .setOrderName(title)
        .setPg("kcp")
        .setOrderId(orderId)
        .setPrice(price)
        .setMethod(method)
}

fun bootPayCreate(
    activity: Activity,
    applicationContext: Context,
    payload: Payload,
    onPayedEvent: (String) -> Unit,
) {
    Bootpay.init(activity, applicationContext)
        .setPayload(payload)
        .setEventListener(object : BootpayEventListener {
            override fun onCancel(data: String?) {
            }

            override fun onError(data: String?) {
            }

            override fun onClose(data: String?) {
                Bootpay.removePaymentWindow()
                kotlin.runCatching {
                    Gson().fromJson(
                        data,
                        BootPayEvent::class.java
                    )
                }.onSuccess {
                    if (it.event == "done") {
                        onPayedEvent(it.data.receipt_id)
                    }
                }
            }

            override fun onIssued(data: String?) {
            }

            override fun onConfirm(data: String?): Boolean {
                return true
            }

            override fun onDone(data: String?) {
            }
        }).requestPayment()
}