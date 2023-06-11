package com.danbam.data.remote.response

import com.danbam.domain.entity.FileEntity
import com.google.gson.annotations.SerializedName

data class FileResponse(
    @SerializedName("file")
    val file: String,
)

fun FileResponse.toEntity() = FileEntity(
    file = file
)