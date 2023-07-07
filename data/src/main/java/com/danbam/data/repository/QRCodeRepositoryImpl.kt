package com.danbam.data.repository

import com.danbam.data.remote.datasource.QRCodeRemoteDataSource
import com.danbam.domain.repository.QRCodeRepository
import java.util.UUID
import javax.inject.Inject

class QRCodeRepositoryImpl @Inject constructor(
    private val qrCodeRemoteDataSource: QRCodeRemoteDataSource
) : QRCodeRepository {
    override suspend fun getQRCode(): UUID = qrCodeRemoteDataSource.getQRCode().uuid
    override suspend fun connectQRCode(uuid: UUID, onSuccess: () -> Unit) =
        qrCodeRemoteDataSource.connectQRCode(uuid = uuid, onSuccess = onSuccess)
}