package com.danbam.domain.usecase.qr_code

import com.danbam.domain.repository.QRCodeRepository
import javax.inject.Inject

class GetQRCodeUseCase @Inject constructor(
    private val qrCodeRepository: QRCodeRepository
) {
    suspend operator fun invoke() = runCatching {
        qrCodeRepository.getQRCode()
    }
}