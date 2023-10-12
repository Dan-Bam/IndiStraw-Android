package com.danbam.indistraw.core.domain.repository

import com.danbam.indistraw.core.entity.banner.BannerEntity

interface BannerRepository {
    suspend fun getBanner(): List<BannerEntity>
}