package com.danbam.data.remote.api

import com.danbam.data.remote.request.ChangePasswordRequest
import retrofit2.http.Body
import retrofit2.http.PATCH

interface AccountAPI {
    @PATCH("account/update/password")
    suspend fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest,
    )
}