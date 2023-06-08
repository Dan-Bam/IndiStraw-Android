package com.danbam.domain.repository

import com.danbam.domain.entity.FindIdEntity
import com.danbam.domain.param.ChangePasswordParam

interface AccountRepository {
    suspend fun findId(phoneNumber: String): FindIdEntity
    suspend fun changePassword(changePasswordParam: ChangePasswordParam)
}