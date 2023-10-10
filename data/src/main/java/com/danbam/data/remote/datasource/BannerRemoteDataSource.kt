package com.danbam.data.remote.datasource

import com.danbam.data.remote.response.banner.BannerResponse

interface BannerRemoteDataSource {
    suspend fun getBanner(): List<BannerResponse>
}