package com.danbam.domain.repository

import com.danbam.domain.param.ChangePasswordParam

interface AccountRepository {
    suspend fun changePassword(changePasswordParam: ChangePasswordParam)
}