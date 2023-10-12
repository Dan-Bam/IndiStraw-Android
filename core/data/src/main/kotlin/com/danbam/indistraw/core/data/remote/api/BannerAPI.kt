package com.danbam.indistraw.core.data.remote.api

import com.danbam.indistraw.core.data.remote.response.banner.BannerResponse
import com.danbam.indistraw.core.data.remote.util.EndPoint
import retrofit2.http.GET

interface BannerAPI {
    @GET("${EndPoint.BANNER}/")
    suspend fun getBanner(): List<BannerResponse>
}