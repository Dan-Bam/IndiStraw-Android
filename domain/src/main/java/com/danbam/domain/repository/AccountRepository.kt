package com.danbam.domain.repository

import com.danbam.domain.entity.FindIdEntity
import com.danbam.domain.entity.ProfileEntity
import com.danbam.domain.param.ChangeAddressParam
import com.danbam.domain.param.ChangePasswordParam
import com.danbam.domain.param.EditProfileParam

interface AccountRepository {
    suspend fun findId(phoneNumber: String): FindIdEntity
    suspend fun changePassword(changePasswordParam: ChangePasswordParam)
    suspend fun getProfile(): ProfileEntity
    suspend fun changePhoneNumber(phoneNumber: String)
    suspend fun changeAddress(changeAddressParam: ChangeAddressParam)
    suspend fun editProfile(editProfileParam: EditProfileParam)
    suspend fun withdraw()
}