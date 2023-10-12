package com.danbam.indistraw.core.data.remote.datasource

import com.danbam.indistraw.core.data.remote.response.banner.BannerResponse

interface BannerRemoteDataSource {
    suspend fun getBanner(): List<BannerResponse>
}