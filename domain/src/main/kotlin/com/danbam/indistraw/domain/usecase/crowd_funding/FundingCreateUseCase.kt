package com.danbam.indistraw.domain.usecase.crowd_funding

import com.danbam.indistraw.domain.param.funding.FundingCreateParam
import com.danbam.indistraw.domain.repository.CrowdFundingRepository
import javax.inject.Inject

class FundingCreateUseCase @Inject constructor(
    private val crowdFundingRepository: CrowdFundingRepository
) {
    suspend operator fun invoke(fundingCreateParam: FundingCreateParam) = runCatching {
        crowdFundingRepository.fundingCreate(fundingCreateParam = fundingCreateParam)
    }
}