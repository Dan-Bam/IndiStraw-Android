package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.ChangePasswordRequest
import com.danbam.data.remote.response.FindIdResponse
import com.danbam.data.remote.response.ProfileResponse

interface AccountRemoteDataSource {
    suspend fun findId(phoneNumber: String): FindIdResponse
    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest)
    suspend fun getProfile(): ProfileResponse
    suspend fun withdraw()
}