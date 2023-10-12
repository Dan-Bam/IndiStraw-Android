package com.danbam.indistraw.data.repository

import com.danbam.indistraw.data.remote.datasource.FileRemoteDataSource
import com.danbam.indistraw.data.remote.response.file.toEntity
import com.danbam.indistraw.data.util.toMultipartBody
import com.danbam.indistraw.domain.entity.file.FileEntity
import com.danbam.indistraw.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileRemoteDataSource: FileRemoteDataSource,
) : FileRepository {
    override suspend fun sendFile(file: File): FileEntity =
        fileRemoteDataSource.sendFile(file = file.toMultipartBody()).toEntity()
}