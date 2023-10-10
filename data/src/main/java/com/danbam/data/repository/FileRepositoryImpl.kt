package com.danbam.data.repository

import com.danbam.data.remote.datasource.FileRemoteDataSource
import com.danbam.data.remote.response.toEntity
import com.danbam.data.util.toMultipartBody
import com.danbam.domain.entity.FileEntity
import com.danbam.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileRemoteDataSource: FileRemoteDataSource,
) : FileRepository {
    override suspend fun sendFile(file: File): FileEntity =
        fileRemoteDataSource.sendFile(file = file.toMultipartBody()).toEntity()
}