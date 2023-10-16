package com.danbam.indistraw.core.remote.api

import com.danbam.indistraw.core.remote.response.banner.BannerResponse
import com.danbam.indistraw.core.remote.util.EndPoint
import retrofit2.http.GET

interface BannerAPI {
    @GET("${EndPoint.BANNER}/")
    suspend fun getBanner(): List<BannerResponse>
}