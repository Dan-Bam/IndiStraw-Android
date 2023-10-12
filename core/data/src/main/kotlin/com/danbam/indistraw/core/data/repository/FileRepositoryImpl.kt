package com.danbam.indistraw.core.data.repository

import com.danbam.indistraw.core.data.remote.datasource.FileRemoteDataSource
import com.danbam.indistraw.core.data.remote.response.file.toEntity
import com.danbam.indistraw.core.data.util.toMultipartBody
import com.danbam.indistraw.core.entity.file.FileEntity
import com.danbam.indistraw.core.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileRemoteDataSource: FileRemoteDataSource,
) : FileRepository {
    override suspend fun sendFile(file: File): FileEntity =
        fileRemoteDataSource.sendFile(file = file.toMultipartBody()).toEntity()
}