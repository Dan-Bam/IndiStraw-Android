package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.ChangePasswordRequest

interface AccountRemoteDataSource {
    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest)
}