package com.danbam.indistraw.data.remote.datasource

import com.danbam.indistraw.data.remote.response.banner.BannerResponse

interface BannerRemoteDataSource {
    suspend fun getBanner(): List<BannerResponse>
}