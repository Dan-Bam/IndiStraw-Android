package com.danbam.data.remote.api

import com.danbam.data.remote.response.BannerResponse
import com.danbam.data.remote.util.EndPoint
import retrofit2.http.GET

interface BannerAPI {
    @GET(EndPoint.BANNER)
    suspend fun getBanner(): List<BannerResponse>
}