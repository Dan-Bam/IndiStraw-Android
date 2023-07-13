package com.danbam.data.remote.response

import com.google.gson.annotations.SerializedName

data class PopularTagResponse(
    @SerializedName("tag_list")
    val tagList: List<String>,
)