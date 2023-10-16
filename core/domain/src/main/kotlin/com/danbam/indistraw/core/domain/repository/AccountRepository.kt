package com.danbam.indistraw.core.domain.repository

import com.danbam.indistraw.core.domain.entity.auth.FindIdEntity
import com.danbam.indistraw.core.domain.entity.auth.ProfileEntity
import com.danbam.indistraw.core.domain.param.auth.ChangeAddressParam
import com.danbam.indistraw.core.domain.param.auth.ChangePasswordParam
import com.danbam.indistraw.core.domain.param.auth.EditProfileParam

interface AccountRepository {
    suspend fun findId(phoneNumber: String): FindIdEntity
    suspend fun changePassword(changePasswordParam: ChangePasswordParam)
    suspend fun getProfile(): ProfileEntity
    suspend fun changePhoneNumber(phoneNumber: String)
    suspend fun changeAddress(changeAddressParam: ChangeAddressParam)
    suspend fun editProfile(editProfileParam: EditProfileParam)
    suspend fun withdraw()
}