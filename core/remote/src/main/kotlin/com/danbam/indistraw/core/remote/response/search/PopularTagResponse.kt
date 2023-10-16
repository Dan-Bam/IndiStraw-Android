package com.danbam.indistraw.core.remote.response.search

import com.google.gson.annotations.SerializedName

data class PopularTagResponse(
    @SerializedName("tagList")
    val tagList: List<String>,
)