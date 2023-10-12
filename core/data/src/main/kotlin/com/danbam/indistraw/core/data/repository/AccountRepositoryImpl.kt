package com.danbam.indistraw.core.data.repository

import com.danbam.indistraw.core.data.remote.datasource.AccountRemoteDataSource
import com.danbam.indistraw.core.data.remote.request.auth.toRequest
import com.danbam.indistraw.core.data.remote.response.auth.toEntity
import com.danbam.indistraw.core.data.remote.response.funding.toEntity
import com.danbam.indistraw.core.domain.entity.auth.FindIdEntity
import com.danbam.indistraw.core.domain.entity.auth.ProfileEntity
import com.danbam.indistraw.core.param.auth.ChangeAddressParam
import com.danbam.indistraw.core.param.auth.ChangePasswordParam
import com.danbam.indistraw.core.param.auth.EditProfileParam
import com.danbam.indistraw.core.domain.repository.AccountRepository
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