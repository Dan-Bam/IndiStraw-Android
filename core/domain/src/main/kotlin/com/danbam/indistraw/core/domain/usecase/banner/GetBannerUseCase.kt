package com.danbam.indistraw.core.domain.usecase.banner

import com.danbam.indistraw.core.domain.repository.BannerRepository
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    suspend operator fun invoke() = runCatching {
        bannerRepository.getBanner()
    }
}