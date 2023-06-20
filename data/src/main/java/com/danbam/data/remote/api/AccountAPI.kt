package com.danbam.data.remote.api

import com.danbam.data.remote.request.ChangeAddressRequest
import com.danbam.data.remote.request.ChangePasswordRequest
import com.danbam.data.remote.request.EditProfileRequest
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

    @PATCH("account/password")
    suspend fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest,
    )

    @GET("account/profile")
    suspend fun getProfile(): ProfileResponse

    @PATCH("account/phone-number/{phoneNumber}")
    suspend fun changePhoneNumber(
        @Path("phoneNumber") phoneNumber: String,
    ): Response<Void?>

    @PATCH("account/address")
    suspend fun changeAddress(
        @Body changeAddressRequest: ChangeAddressRequest,
    ): Response<Void?>

    @PATCH("account/profile")
    suspend fun editProfile(
        @Body editProfileRequest: EditProfileRequest,
    ): Response<Void?>

    @DELETE("account")
    suspend fun withdraw(): Response<Void?>
}