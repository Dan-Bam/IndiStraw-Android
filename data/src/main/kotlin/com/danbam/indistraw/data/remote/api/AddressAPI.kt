package com.danbam.indistraw.data.remote.api

import com.danbam.indistraw.data.BuildConfig
import com.danbam.indistraw.data.remote.response.auth.AddressResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface AddressAPI {
    @GET
    suspend fun getAddress(
        @Url url: String = "https://business.juso.go.kr/addrlink/addrLinkApi.do",
        @Query("confmKey") confmKey: String = BuildConfig.JUSO_KEY,
        @Query("currentPage") currentPage: Int = 1,
        @Query("resultType") resultType: String = "json",
        @Query("keyword") keyword: String,
    ): AddressResponse
}