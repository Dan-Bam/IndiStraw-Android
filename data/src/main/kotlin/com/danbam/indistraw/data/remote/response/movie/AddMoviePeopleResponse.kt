package com.danbam.indistraw.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class AddMoviePeopleResponse(
    @SerializedName("idx")
    val actorIdx: Long,
)
