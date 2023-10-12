package com.danbam.indistraw.core.data.remote.request.funding

import com.danbam.indistraw.core.param.funding.FundingParam
import com.google.gson.annotations.SerializedName

data class FundingRequest(
    @SerializedName("receiptId")
    val receiptId: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("extraPrice")
    val extraPrice: Long,
)

fun FundingParam.toRequest() = FundingRequest(
    receiptId = receiptId,
    price = price,
    extraPrice = extraPrice
)