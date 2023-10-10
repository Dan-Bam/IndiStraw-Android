package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.AccountAPI
import com.danbam.data.remote.request.ChangeAddressRequest
import com.danbam.data.remote.request.ChangePasswordRequest
import com.danbam.data.remote.request.EditProfileRequest
import com.danbam.data.remote.response.FindIdResponse
import com.danbam.data.remote.response.ProfileResponse
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class AccountRemoteDataSourceImpl @Inject constructor(
    private val accountAPI: AccountAPI,
) : AccountRemoteDataSource {
    override suspend fun findId(phoneNumber: String): FindIdResponse = indiStrawApiCall {
        accountAPI.findId(phoneNumber = phoneNumber)
    }

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) =
        indiStrawApiCall {
            accountAPI.changePassword(changePasswordRequest = changePasswordRequest)
        }

    override suspend fun getProfile(): ProfileResponse = indiStrawApiCall {
        accountAPI.getProfile()
    }

    override suspend fun changePhoneNumber(phoneNumber: String) = indiStrawApiCall {
        accountAPI.changePhoneNumber(phoneNumber = phoneNumber).errorHandling()
    }

    override suspend fun changeAddress(changeAddressRequest: ChangeAddressRequest) =
        indiStrawApiCall {
            accountAPI.changeAddress(changeAddressRequest = changeAddressRequest).errorHandling()
        }

    override suspend fun editProfile(editProfileRequest: EditProfileRequest) = indiStrawApiCall {
        accountAPI.editProfile(editProfileRequest = editProfileRequest).errorHandling()
    }

    override suspend fun withdraw() = indiStrawApiCall {
        accountAPI.withdraw().errorHandling()
    }
}