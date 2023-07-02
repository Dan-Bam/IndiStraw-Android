package com.danbam.domain.usecase.funding

import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingDetailUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(fundingIndex: Long) = runCatching {
        fundingRepository.fundingDetail(fundingIndex = fundingIndex)
    }
}