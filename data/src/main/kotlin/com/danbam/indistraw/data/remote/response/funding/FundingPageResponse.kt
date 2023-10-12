package com.danbam.indistraw.data.remote.response.funding

import com.google.gson.annotations.SerializedName

data class FundingPageResponse(
    @SerializedName("isLast")
    val isLast: Boolean,
    @SerializedName("list")
    val list: List<FundingResponse>,
)
