package com.danbam.domain.usecase.banner

import com.danbam.domain.repository.BannerRepository
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    suspend operator fun invoke() = runCatching {
        bannerRepository.getBanner()
    }
}