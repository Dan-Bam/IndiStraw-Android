package com.danbam.indistraw.data.remote.api

import com.danbam.indistraw.data.remote.response.banner.BannerResponse
import com.danbam.indistraw.data.remote.util.EndPoint
import retrofit2.http.GET

interface BannerAPI {
    @GET("${EndPoint.BANNER}/")
    suspend fun getBanner(): List<BannerResponse>
}