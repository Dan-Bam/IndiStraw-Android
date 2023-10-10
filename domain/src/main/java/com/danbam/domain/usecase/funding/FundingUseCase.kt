package com.danbam.domain.usecase.funding

import com.danbam.domain.param.funding.FundingParam
import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(fundingIdx: Long, rewardIdx: Long, fundingParam: FundingParam) = runCatching {
        fundingRepository.funding(fundingIdx = fundingIdx, rewardIdx = rewardIdx, fundingParam = fundingParam)
    }
}