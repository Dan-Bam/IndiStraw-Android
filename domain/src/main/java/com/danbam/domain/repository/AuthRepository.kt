package com.danbam.domain.repository

import com.danbam.domain.param.LoginParam
import com.danbam.domain.param.SignUpParam

interface AuthRepository {
    suspend fun signup(signUpParam: SignUpParam)
    suspend fun login(loginParam: LoginParam)
    suspend fun isLogin()
    suspend fun checkPhoneNumber(phoneNumber: String, type: String)
    suspend fun checkId(id: String)
    suspend fun sendCertificateNumber(phoneNumber: String)
    suspend fun checkCertificateNumber(authCode: Int, phoneNumber: String)
    suspend fun logout()
    suspend fun clearToken()
}