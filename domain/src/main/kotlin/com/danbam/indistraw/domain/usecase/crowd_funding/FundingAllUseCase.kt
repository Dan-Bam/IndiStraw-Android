package com.danbam.indistraw.domain.usecase.crowd_funding

import com.danbam.indistraw.domain.repository.CrowdFundingRepository
import javax.inject.Inject

class FundingAllUseCase @Inject constructor(
    private val crowdFundingRepository: CrowdFundingRepository
) {
    suspend operator fun invoke() = runCatching {
        crowdFundingRepository.fundingAll()
    }
}