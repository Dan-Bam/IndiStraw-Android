package com.danbam.domain.usecase.crowd_funding

import com.danbam.domain.repository.CrowdFundingRepository
import javax.inject.Inject

class FundingAllUseCase @Inject constructor(
    private val crowdFundingRepository: CrowdFundingRepository
) {
    suspend operator fun invoke() = runCatching {
        crowdFundingRepository.fundingAll()
    }
}