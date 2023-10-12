package com.danbam.indistraw.data.remote.datasource

import com.danbam.indistraw.data.remote.request.auth.ChangeAddressRequest
import com.danbam.indistraw.data.remote.request.auth.ChangePasswordRequest
import com.danbam.indistraw.data.remote.request.auth.EditProfileRequest
import com.danbam.indistraw.data.remote.response.auth.ProfileResponse
import com.danbam.indistraw.data.remote.response.funding.FindIdResponse

interface AccountRemoteDataSource {
    suspend fun findId(phoneNumber: String): FindIdResponse
    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest)
    suspend fun getProfile(): ProfileResponse
    suspend fun changePhoneNumber(phoneNumber: String)
    suspend fun changeAddress(changeAddressRequest: ChangeAddressRequest)
    suspend fun editProfile(editProfileRequest: EditProfileRequest)
    suspend fun withdraw()
}