package com.danbam.indistraw.data.repository

import com.danbam.indistraw.data.remote.datasource.BannerRemoteDataSource
import com.danbam.indistraw.data.remote.response.banner.toEntity
import com.danbam.indistraw.domain.entity.banner.BannerEntity
import com.danbam.indistraw.domain.repository.BannerRepository
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val bannerRemoteDataSource: BannerRemoteDataSource
) : BannerRepository {
    override suspend fun getBanner(): List<BannerEntity> =
        bannerRemoteDataSource.getBanner().map { it.toEntity() }
}