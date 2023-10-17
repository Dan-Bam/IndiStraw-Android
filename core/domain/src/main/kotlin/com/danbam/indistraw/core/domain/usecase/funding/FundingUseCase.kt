package com.danbam.indistraw.core.domain.usecase.funding

import com.danbam.indistraw.core.domain.param.funding.FundingParam
import com.danbam.indistraw.core.domain.repository.FundingRepository
import javax.inject.Inject

class FundingUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(fundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam) = runCatching {
        fundingRepository.funding(fundingIdx = fundingIdx, rewardIdx = rewardIdx, fundingParam = fundingParam)
    }
}