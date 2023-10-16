package com.danbam.indistraw.core.remote.request.auth

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class CheckQRCodeRequest(
    @SerializedName("uuid")
    val uuid: UUID,
)
