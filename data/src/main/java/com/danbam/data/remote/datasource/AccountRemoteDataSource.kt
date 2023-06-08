package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.ChangePasswordRequest
import com.danbam.data.remote.response.FindIdResponse

interface AccountRemoteDataSource {
    suspend fun findId(phoneNumber: String): FindIdResponse
    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest)
}