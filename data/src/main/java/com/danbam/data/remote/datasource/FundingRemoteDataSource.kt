package com.danbam.data.remote.datasource

import com.danbam.data.remote.response.ReceiptResponse

interface FundingRemoteDataSource {
    suspend fun getReceipt(): ReceiptResponse
}