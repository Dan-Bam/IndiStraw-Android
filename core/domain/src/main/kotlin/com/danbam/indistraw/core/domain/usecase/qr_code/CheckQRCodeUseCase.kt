package com.danbam.indistraw.core.domain.usecase.qr_code

import com.danbam.indistraw.core.domain.repository.QRCodeRepository
import java.util.UUID
import javax.inject.Inject

class CheckQRCodeUseCase @Inject constructor(
    private val qrCodeRepository: QRCodeRepository
) {
    suspend operator fun invoke(uuid: UUID) = runCatching {
        qrCodeRepository.checkQRCode(uuid = uuid)
    }
}