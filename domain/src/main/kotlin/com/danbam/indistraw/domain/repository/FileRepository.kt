package com.danbam.indistraw.domain.repository

import com.danbam.indistraw.domain.entity.file.FileEntity
import java.io.File

interface FileRepository {
    suspend fun sendFile(file: File): FileEntity
}