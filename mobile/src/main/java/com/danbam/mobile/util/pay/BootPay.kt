package com.danbam.mobile.util.pay

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.danbam.mobile.BuildConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.Payload

data class BootPayEvent(
    @SerializedName("event")
    val event: String,
)

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
    onPayedEvent: () -> Unit,
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
                        onPayedEvent()
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