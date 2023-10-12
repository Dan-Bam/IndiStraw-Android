package com.danbam.indistraw.domain.repository

import com.danbam.indistraw.domain.entity.banner.BannerEntity

interface BannerRepository {
    suspend fun getBanner(): List<BannerEntity>
}