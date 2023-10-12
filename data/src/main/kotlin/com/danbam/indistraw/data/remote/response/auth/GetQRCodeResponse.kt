package com.danbam.indistraw.data.remote.response.auth

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class GetQRCodeResponse(
    @SerializedName("uuid")
    val uuid: UUID,
)
