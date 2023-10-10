package com.danbam.data.remote.response.auth

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class GetQRCodeResponse(
    @SerializedName("uuid")
    val uuid: UUID,
)
