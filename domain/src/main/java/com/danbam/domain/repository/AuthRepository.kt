package com.danbam.domain.repository

import com.danbam.domain.entity.LoginEntity
import com.danbam.domain.param.LoginParam

interface AuthRepository {
    suspend fun login(loginParam: LoginParam)
    suspend fun isLogin()
    suspend fun checkPhoneNumber(phoneNumber: String): Void
    suspend fun sendCertificateNumber(phoneNumber: String)
    suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String)
}