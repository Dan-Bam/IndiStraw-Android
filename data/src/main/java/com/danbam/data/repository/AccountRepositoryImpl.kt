package com.danbam.data.repository

import com.danbam.data.remote.datasource.AccountRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.domain.param.ChangePasswordParam
import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
) : AccountRepository {
    override suspend fun changePassword(changePasswordParam: ChangePasswordParam) =
        accountRemoteDataSource.changePassword(changePasswordRequest = changePasswordParam.toRequest())
}