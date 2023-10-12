package com.danbam.indistraw.core.domain.usecase.qr_code

import com.danbam.indistraw.core.domain.repository.QRCodeRepository
import javax.inject.Inject

class GetQRCodeUseCase @Inject constructor(
    private val qrCodeRepository: QRCodeRepository
) {
    suspend operator fun invoke() = runCatching {
        qrCodeRepository.getQRCode()
    }
}