package com.danbam.indistraw.core.domain.repository

import com.danbam.indistraw.core.entity.file.FileEntity
import java.io.File

interface FileRepository {
    suspend fun sendFile(file: File): FileEntity
}