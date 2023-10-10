package com.danbam.data.repository

import com.danbam.data.remote.datasource.BannerRemoteDataSource
import com.danbam.data.remote.response.banner.toEntity
import com.danbam.domain.entity.banner.BannerEntity
import com.danbam.domain.repository.BannerRepository
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val bannerRemoteDataSource: BannerRemoteDataSource
) : BannerRepository {
    override suspend fun getBanner(): List<BannerEntity> =
        bannerRemoteDataSource.getBanner().map { it.toEntity() }
}