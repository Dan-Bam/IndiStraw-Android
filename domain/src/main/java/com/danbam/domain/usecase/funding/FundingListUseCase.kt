package com.danbam.domain.usecase.funding

import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingListUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke() = runCatching {
        fundingRepository.fundingList()
    }
}