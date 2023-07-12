package com.danbam.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReceiptResponse(
    @SerializedName("receiptId")
    val receiptId: String,
)