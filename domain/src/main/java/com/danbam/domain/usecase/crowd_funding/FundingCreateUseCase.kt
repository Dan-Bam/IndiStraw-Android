package com.danbam.domain.usecase.crowd_funding

import com.danbam.domain.param.FundingCreateParam
import com.danbam.domain.repository.CrowdFundingRepository
import javax.inject.Inject

class FundingCreateUseCase @Inject constructor(
    private val crowdFundingRepository: CrowdFundingRepository
) {
    suspend operator fun invoke(fundingCreateParam: FundingCreateParam) = runCatching {
        crowdFundingRepository.fundingCreate(fundingCreateParam = fundingCreateParam)
    }
}