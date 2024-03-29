package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.api.AccountAPI
import com.danbam.indistraw.core.remote.request.auth.ChangeAddressRequest
import com.danbam.indistraw.core.remote.request.auth.ChangePasswordRequest
import com.danbam.indistraw.core.remote.request.auth.EditProfileRequest
import com.danbam.indistraw.core.remote.response.auth.ProfileResponse
import com.danbam.indistraw.core.remote.response.funding.FindIdResponse
import com.danbam.indistraw.core.remote.util.errorHandling
import com.danbam.indistraw.core.remote.util.indiStrawApiCall
import javax.inject.Inject

class AccountRemoteDataSourceImpl @Inject constructor(
    private val accountAPI: AccountAPI,
) : AccountRemoteDataSource {
    override suspend fun findId(phoneNumber: String): FindIdResponse =
        indiStrawApiCall {
            accountAPI.findId(phoneNumber = phoneNumber)
        }

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) =
        indiStrawApiCall {
            accountAPI.changePassword(changePasswordRequest = changePasswordRequest)
        }

    override suspend fun getProfile(): ProfileResponse =
        indiStrawApiCall {
            accountAPI.getProfile()
        }

    override suspend fun changePhoneNumber(phoneNumber: String) =
        indiStrawApiCall {
            accountAPI.changePhoneNumber(phoneNumber = phoneNumber).errorHandling()
        }

    override suspend fun changeAddress(changeAddressRequest: ChangeAddressRequest) =
        indiStrawApiCall {
            accountAPI.changeAddress(changeAddressRequest = changeAddressRequest).errorHandling()
        }

    override suspend fun editProfile(editProfileRequest: EditProfileRequest) =
        indiStrawApiCall {
            accountAPI.editProfile(editProfileRequest = editProfileRequest).errorHandling()
        }

    override suspend fun enrollMoviePeople(peopleType: String, actorIdx: Long) = indiStrawApiCall {
        accountAPI.enrollMoviePeople(peopleType = peopleType, actorIdx = actorIdx).errorHandling()
    }

    override suspend fun withdraw() = indiStrawApiCall {
        accountAPI.withdraw().errorHandling()
    }
}