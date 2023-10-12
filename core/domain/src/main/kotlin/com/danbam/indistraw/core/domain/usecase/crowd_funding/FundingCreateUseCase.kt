package com.danbam.indistraw.core.domain.usecase.crowd_funding

import com.danbam.indistraw.core.param.funding.FundingCreateParam
import com.danbam.indistraw.core.domain.repository.CrowdFundingRepository
import javax.inject.Inject

class FundingCreateUseCase @Inject constructor(
    private val crowdFundingRepository: CrowdFundingRepository
) {
    suspend operator fun invoke(fundingCreateParam: FundingCreateParam) = runCatching {
        crowdFundingRepository.fundingCreate(fundingCreateParam = fundingCreateParam)
    }
}