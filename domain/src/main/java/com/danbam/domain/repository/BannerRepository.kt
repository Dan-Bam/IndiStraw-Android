package com.danbam.domain.repository

import com.danbam.domain.entity.banner.BannerEntity

interface BannerRepository {
    suspend fun getBanner(): List<BannerEntity>
}