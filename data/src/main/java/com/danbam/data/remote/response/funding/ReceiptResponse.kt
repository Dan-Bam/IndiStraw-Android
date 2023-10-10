package com.danbam.data.remote.response.funding

import com.google.gson.annotations.SerializedName

data class ReceiptResponse(
    @SerializedName("receiptId")
    val receiptId: String,
)