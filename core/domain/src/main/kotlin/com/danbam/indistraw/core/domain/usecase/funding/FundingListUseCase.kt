package com.danbam.indistraw.core.domain.usecase.funding

import com.danbam.indistraw.core.domain.repository.FundingRepository
import javax.inject.Inject

class FundingListUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke() = runCatching {
        fundingRepository.fundingList()
    }
}