package com.danbam.domain.repository

import com.danbam.domain.entity.LoginEntity
import com.danbam.domain.param.LoginParam
import com.danbam.domain.param.SignUpParam

interface AuthRepository {
    suspend fun signup(signUpParam: SignUpParam)
    suspend fun login(loginParam: LoginParam)
    suspend fun isLogin()
    suspend fun checkPhoneNumber(phoneNumber: String, type: String): Void
    suspend fun checkId(id: String): Void
    suspend fun sendCertificateNumber(phoneNumber: String)
    suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String)
}