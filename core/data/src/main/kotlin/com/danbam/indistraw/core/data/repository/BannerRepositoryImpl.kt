package com.danbam.indistraw.core.data.repository

import com.danbam.indistraw.core.domain.entity.banner.BannerEntity
import com.danbam.indistraw.core.remote.datasource.BannerRemoteDataSource
import com.danbam.indistraw.core.domain.repository.BannerRepository
import com.danbam.indistraw.core.remote.response.banner.toEntity
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val bannerRemoteDataSource: BannerRemoteDataSource
) : BannerRepository {
    override suspend fun getBanner(): List<BannerEntity> =
        bannerRemoteDataSource.getBanner().map { it.toEntity() }
}