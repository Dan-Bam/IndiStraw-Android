package com.danbam.indistraw.domain.usecase.banner

import com.danbam.indistraw.domain.repository.BannerRepository
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    suspend operator fun invoke() = runCatching {
        bannerRepository.getBanner()
    }
}