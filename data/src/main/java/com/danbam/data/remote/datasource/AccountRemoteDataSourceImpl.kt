package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.AccountAPI
import com.danbam.data.remote.request.ChangePasswordRequest
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class AccountRemoteDataSourceImpl @Inject constructor(
    private val accountAPI: AccountAPI,
) : AccountRemoteDataSource {
    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) =
        indiStrawApiCall {
            accountAPI.changePassword(changePasswordRequest = changePasswordRequest)
        }
}