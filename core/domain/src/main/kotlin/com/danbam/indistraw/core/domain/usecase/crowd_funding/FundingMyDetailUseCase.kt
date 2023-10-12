package com.danbam.indistraw.core.domain.usecase.crowd_funding

import com.danbam.indistraw.core.domain.repository.CrowdFundingRepository
import javax.inject.Inject

class FundingMyDetailUseCase @Inject constructor(
    private val crowdFundingRepository: CrowdFundingRepository
) {
    suspend operator fun invoke(fundingIdx: Long) = runCatching {
        crowdFundingRepository.fundingMyDetail(fundingIdx = fundingIdx)
    }
}