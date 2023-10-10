package com.danbam.data.remote.datasource

import com.danbam.data.remote.request.auth.ChangeAddressRequest
import com.danbam.data.remote.request.auth.ChangePasswordRequest
import com.danbam.data.remote.request.auth.EditProfileRequest
import com.danbam.data.remote.response.funding.FindIdResponse
import com.danbam.data.remote.response.auth.ProfileResponse

interface AccountRemoteDataSource {
    suspend fun findId(phoneNumber: String): FindIdResponse
    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest)
    suspend fun getProfile(): ProfileResponse
    suspend fun changePhoneNumber(phoneNumber: String)
    suspend fun changeAddress(changeAddressRequest: ChangeAddressRequest)
    suspend fun editProfile(editProfileRequest: EditProfileRequest)
    suspend fun withdraw()
}