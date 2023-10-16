package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.response.banner.BannerResponse

interface BannerRemoteDataSource {
    suspend fun getBanner(): List<BannerResponse>
}