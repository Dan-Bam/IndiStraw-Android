package com.danbam.domain.repository

import com.danbam.domain.entity.file.FileEntity
import java.io.File

interface FileRepository {
    suspend fun sendFile(file: File): FileEntity
}