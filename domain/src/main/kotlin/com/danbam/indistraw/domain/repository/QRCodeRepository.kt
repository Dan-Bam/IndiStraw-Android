package com.danbam.indistraw.domain.repository

import java.util.UUID

interface QRCodeRepository {
    suspend fun getQRCode(): UUID
    suspend fun checkQRCode(uuid: UUID)
    suspend fun connectQRCode(uuid: UUID, onSuccess: () -> Unit)
}