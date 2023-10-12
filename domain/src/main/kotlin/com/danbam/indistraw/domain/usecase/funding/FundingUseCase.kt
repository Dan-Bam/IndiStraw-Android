package com.danbam.indistraw.domain.usecase.funding

import com.danbam.indistraw.domain.param.funding.FundingParam
import com.danbam.indistraw.domain.repository.FundingRepository
import javax.inject.Inject

class FundingUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(fundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam) = runCatching {
        fundingRepository.funding(fundingIdx = fundingIdx, rewardIdx = rewardIdx, fundingParam = fundingParam)
    }
}