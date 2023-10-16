package com.danbam.indistraw.core.remote.api

import com.danbam.indistraw.core.remote.response.auth.ProfileResponse
import com.danbam.indistraw.core.remote.response.funding.FindIdResponse
import com.danbam.indistraw.core.remote.util.EndPoint.Companion.ACCOUNT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AccountAPI {
    @GET("${ACCOUNT}/phone-number/{phoneNumber}")
    suspend fun findId(
        @Path("phoneNumber") phoneNumber: String,
    ): FindIdResponse

    @PATCH("${ACCOUNT}/password")
    suspend fun changePassword(
        @Body changePasswordRequest: com.danbam.indistraw.core.remote.request.auth.ChangePasswordRequest,
    )

    @GET("${ACCOUNT}/info")
    suspend fun getProfile(): ProfileResponse

    @PATCH("${ACCOUNT}/phone-number/{phoneNumber}")
    suspend fun changePhoneNumber(
        @Path("phoneNumber") phoneNumber: String,
    ): Response<Void?>

    @PATCH("${ACCOUNT}/address")
    suspend fun changeAddress(
        @Body changeAddressRequest: com.danbam.indistraw.core.remote.request.auth.ChangeAddressRequest,
    ): Response<Void?>

    @PATCH("${ACCOUNT}/info")
    suspend fun editProfile(
        @Body editProfileRequest: com.danbam.indistraw.core.remote.request.auth.EditProfileRequest,
    ): Response<Void?>

    @DELETE(ACCOUNT)
    suspend fun withdraw(): Response<Void?>
}