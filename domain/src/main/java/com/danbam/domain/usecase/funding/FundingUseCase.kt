package com.danbam.domain.usecase.funding

import com.danbam.domain.param.FundingParam
import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(crowdfundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam) = runCatching {
        fundingRepository.funding(crowdfundingIdx = crowdfundingIdx, rewardIdx = rewardIdx, fundingParam = fundingParam)
    }
}