package com.danbam.data.remote.response

import com.google.gson.annotations.SerializedName

data class AddMoviePeopleResponse(
    @SerializedName("actor_idx")
    val actorIdx: Int,
)
