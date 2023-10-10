package com.danbam.data.remote.response

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class GetQRCodeResponse(
    @SerializedName("uuid")
    val uuid: UUID,
)
