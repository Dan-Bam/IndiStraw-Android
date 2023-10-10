package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.BannerAPI
import com.danbam.data.remote.response.banner.BannerResponse
import com.danbam.data.remote.util.indiStrawApiCall
import javax.inject.Inject

class BannerRemoteDataSourceImpl @Inject constructor(
    private val bannerAPI: BannerAPI
) : BannerRemoteDataSource {
    override suspend fun getBanner(): List<BannerResponse> = indiStrawApiCall {
        bannerAPI.getBanner()
    }
}