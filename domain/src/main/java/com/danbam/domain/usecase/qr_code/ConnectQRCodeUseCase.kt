package com.danbam.domain.usecase.qr_code

import com.danbam.domain.repository.QRCodeRepository
import java.util.UUID
import javax.inject.Inject

class ConnectQRCodeUseCase @Inject constructor(
    private val qrCodeRepository: QRCodeRepository
) {
    suspend operator fun invoke(uuid: UUID, onSuccess: () -> Unit) = runCatching {
        qrCodeRepository.connectQRCode(uuid = uuid, onSuccess = onSuccess)
    }
}