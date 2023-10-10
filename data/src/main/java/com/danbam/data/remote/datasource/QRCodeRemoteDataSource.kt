package com.danbam.data.remote.datasource

import com.danbam.data.remote.response.auth.GetQRCodeResponse
import com.danbam.data.remote.response.auth.LoginResponse
import java.util.UUID

interface QRCodeRemoteDataSource {
    suspend fun getQRCode(): GetQRCodeResponse
    suspend fun checkQRCode(uuid: UUID)
    suspend fun connectQRCode(uuid: UUID, onSuccess: (LoginResponse) -> Unit)
}