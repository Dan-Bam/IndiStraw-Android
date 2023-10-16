package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.response.auth.GetQRCodeResponse
import com.danbam.indistraw.core.remote.response.auth.LoginResponse
import java.util.UUID

interface QRCodeRemoteDataSource {
    suspend fun getQRCode(): GetQRCodeResponse
    suspend fun checkQRCode(uuid: UUID)
    suspend fun connectQRCode(uuid: UUID, onSuccess: (LoginResponse) -> Unit)
}