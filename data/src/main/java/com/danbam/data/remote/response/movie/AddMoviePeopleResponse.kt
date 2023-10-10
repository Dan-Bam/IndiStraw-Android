package com.danbam.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class AddMoviePeopleResponse(
    @SerializedName("idx")
    val actorIdx: Long,
)
