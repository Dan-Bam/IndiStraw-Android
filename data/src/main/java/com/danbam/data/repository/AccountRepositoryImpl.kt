package com.danbam.data.repository

import com.danbam.data.remote.datasource.AccountRemoteDataSource
import com.danbam.data.remote.request.auth.toRequest
import com.danbam.data.remote.response.auth.toEntity
import com.danbam.data.remote.response.funding.toEntity
import com.danbam.domain.entity.auth.FindIdEntity
import com.danbam.domain.entity.auth.ProfileEntity
import com.danbam.domain.param.auth.ChangeAddressParam
import com.danbam.domain.param.auth.ChangePasswordParam
import com.danbam.domain.param.auth.EditProfileParam
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

    override suspend fun changeAddress(changeAddressParam: ChangeAddressParam) =
        accountRemoteDataSource.changeAddress(changeAddressRequest = changeAddressParam.toRequest())

    override suspend fun editProfile(editProfileParam: EditProfileParam) =
        accountRemoteDataSource.editProfile(editProfileRequest = editProfileParam.toRequest())

    override suspend fun withdraw() =
        accountRemoteDataSource.withdraw()
}