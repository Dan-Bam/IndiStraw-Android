package com.danbam.data.remote.response

import com.google.gson.annotations.SerializedName

data class PopularTagResponse(
    @SerializedName("tagList")
    val tagList: List<String>,
)