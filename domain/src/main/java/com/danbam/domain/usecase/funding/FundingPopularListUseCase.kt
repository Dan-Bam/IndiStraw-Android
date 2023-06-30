package com.danbam.domain.usecase.funding

import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingPopularListUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke() = runCatching {
        fundingRepository.fundingPopularList()
    }
}