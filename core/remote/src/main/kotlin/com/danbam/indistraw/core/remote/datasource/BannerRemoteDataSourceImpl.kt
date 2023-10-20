package com.danbam.indistraw.core.remote.datasource

import com.danbam.indistraw.core.remote.api.BannerAPI
import com.danbam.indistraw.core.remote.response.banner.BannerResponse
import com.danbam.indistraw.core.remote.util.indiStrawApiCall
import javax.inject.Inject

class BannerRemoteDataSourceImpl @Inject constructor(
    private val bannerAPI: BannerAPI
) : BannerRemoteDataSource {
    override suspend fun getBanner(): List<BannerResponse> =
        indiStrawApiCall {
            bannerAPI.getBanner()
        }
}