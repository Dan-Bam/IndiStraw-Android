package com.danbam.domain.repository

interface FundingRepository {
    suspend fun getReceipt(): String
}