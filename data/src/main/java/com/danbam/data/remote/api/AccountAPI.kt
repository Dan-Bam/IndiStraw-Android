package com.danbam.data.remote.api

import com.danbam.data.remote.request.ChangePasswordRequest
import com.danbam.data.remote.response.FindIdResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AccountAPI {
    @GET("account/phone-number/{phoneNumber}")
    suspend fun findId(
        @Path("phoneNumber") phoneNumber: String,
    ): FindIdResponse

    @PATCH("account/update/password")
    suspend fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest,
    )
}