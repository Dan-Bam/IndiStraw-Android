package com.danbam.domain.repository

import com.danbam.domain.entity.auth.FindIdEntity
import com.danbam.domain.entity.auth.ProfileEntity
import com.danbam.domain.param.auth.ChangeAddressParam
import com.danbam.domain.param.auth.ChangePasswordParam
import com.danbam.domain.param.auth.EditProfileParam

interface AccountRepository {
    suspend fun findId(phoneNumber: String): FindIdEntity
    suspend fun changePassword(changePasswordParam: ChangePasswordParam)
    suspend fun getProfile(): ProfileEntity
    suspend fun changePhoneNumber(phoneNumber: String)
    suspend fun changeAddress(changeAddressParam: ChangeAddressParam)
    suspend fun editProfile(editProfileParam: EditProfileParam)
    suspend fun withdraw()
}