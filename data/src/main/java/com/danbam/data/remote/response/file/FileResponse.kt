package com.danbam.data.remote.response.file

import com.danbam.domain.entity.file.FileEntity
import com.google.gson.annotations.SerializedName

data class FileResponse(
    @SerializedName("imageUrl")
    val file: String,
)

fun FileResponse.toEntity() = FileEntity(
    file = "https://indistraw-s3.s3.ap-northeast-2.amazonaws.com/$file"
)