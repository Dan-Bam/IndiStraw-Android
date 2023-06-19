package com.danbam.data.repository

import com.danbam.data.remote.datasource.AccountRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.entity.FindIdEntity
import com.danbam.domain.entity.ProfileEntity
import com.danbam.domain.param.ChangePasswordParam
import com.danbam.domain.param.EditProfileParam
import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
) : AccountRepository {
    override suspend fun findId(phoneNumber: String): FindIdEntity =
        accountRemoteDataSource.findId(phoneNumber = phoneNumber).toEntity()

    override suspend fun changePassword(changePasswordParam: ChangePasswordParam) =
        accountRemoteDataSource.changePassword(changePasswordRequest = changePasswordParam.toRequest())

    override suspend fun getProfile(): ProfileEntity =
        accountRemoteDataSource.getProfile().toEntity()

    override suspend fun changePhoneNumber(phoneNumber: String) =
        accountRemoteDataSource.changePhoneNumber(phoneNumber = phoneNumber)

    override suspend fun editProfile(editProfileParam: EditProfileParam) =
        accountRemoteDataSource.editProfile(editProfileRequest = editProfileParam.toRequest())

    override suspend fun withdraw() =
        accountRemoteDataSource.withdraw()
}