package com.danbam.indistraw.core.data.repository

import com.danbam.indistraw.core.local.datasource.AuthLocalDataSource
import com.danbam.indistraw.core.domain.repository.QRCodeRepository
import com.danbam.indistraw.core.remote.datasource.QRCodeRemoteDataSource
import java.util.UUID
import javax.inject.Inject

class QRCodeRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val qrCodeRemoteDataSource: QRCodeRemoteDataSource
) : QRCodeRepository {
    override suspend fun getQRCode(): UUID = qrCodeRemoteDataSource.getQRCode().uuid
    override suspend fun checkQRCode(uuid: UUID) = qrCodeRemoteDataSource.checkQRCode(uuid = uuid)

    override suspend fun connectQRCode(uuid: UUID, onSuccess: () -> Unit) {
        qrCodeRemoteDataSource.connectQRCode(uuid = uuid) {
            runCatching {
                authLocalDataSource.saveAccessToken(it.accessToken)
                authLocalDataSource.saveRefreshToken(it.refreshToken)
                authLocalDataSource.saveAccessExpiredAt(it.accessExpiredAt)
                authLocalDataSource.saveRefreshExpiredAt(it.refreshExpiredAt)
            }.onSuccess {
                onSuccess()
            }
        }
    }
}