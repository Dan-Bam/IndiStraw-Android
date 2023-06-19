package com.danbam.data.remote.api

import com.danbam.data.remote.request.ChangePasswordRequest
import com.danbam.data.remote.response.FindIdResponse
import com.danbam.data.remote.response.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
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

    @GET("account/profile")
    suspend fun getProfile(): ProfileResponse

    @DELETE("account/withdraw")
    suspend fun withdraw(): Response<Void?>
}