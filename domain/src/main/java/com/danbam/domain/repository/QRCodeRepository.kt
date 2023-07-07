package com.danbam.domain.repository

import java.util.UUID

interface QRCodeRepository {
    suspend fun getQRCode(): UUID
    suspend fun connectQRCode(uuid: UUID, onSuccess: () -> Unit)
}