package com.danbam.data.remote.response.search

import com.danbam.domain.entity.search.RelatedSearchEntity
import com.google.gson.annotations.SerializedName

data class RelatedSearchResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
)

fun RelatedSearchResponse.toEntity() = RelatedSearchEntity(
    id = id,
    title = title
)
