package com.danbam.data.remote.response

import com.danbam.domain.entity.RelatedSearchEntity
import com.google.gson.annotations.SerializedName

data class RelatedSearchResponse(
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("isLast")
    val isLast: Boolean,
    @SerializedName("searchList")
    val searchList: List<RelatedSearchItem>,
) {
    data class RelatedSearchItem(
        @SerializedName("title")
        val title: String,
    )
}

fun RelatedSearchResponse.RelatedSearchItem.toEntity() = RelatedSearchEntity(
    title = title
)
