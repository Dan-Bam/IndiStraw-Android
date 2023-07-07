package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.QRCodeAPI
import com.danbam.data.remote.response.GetQRCodeResponse
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class QRCodeRemoteDataSourceImpl @Inject constructor(
    private val qrCodeAPI: QRCodeAPI
) : QRCodeRemoteDataSource {
    override suspend fun getQRCode(): GetQRCodeResponse = indiStrawApiCall {
        qrCodeAPI.getQRCode()
    }
}