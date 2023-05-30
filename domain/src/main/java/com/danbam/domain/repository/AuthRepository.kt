package com.danbam.domain.repository

import com.danbam.domain.entity.LoginEntity
import com.danbam.domain.param.LoginParam

interface AuthRepository {
    suspend fun login(loginParam: LoginParam): LoginEntity
}