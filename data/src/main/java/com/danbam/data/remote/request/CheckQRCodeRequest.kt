package com.danbam.data.remote.request

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class CheckQRCodeRequest(
    @SerializedName("uuid")
    val uuid: UUID,
)
