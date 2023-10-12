package com.danbam.indistraw.domain.usecase.funding

import com.danbam.indistraw.domain.repository.FundingRepository
import javax.inject.Inject

class FundingListUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke() = runCatching {
        fundingRepository.fundingList()
    }
}