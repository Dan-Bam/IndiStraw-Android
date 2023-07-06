package com.danbam.domain.usecase.funding

import com.danbam.domain.param.FundingCreateParam
import com.danbam.domain.repository.FundingRepository
import javax.inject.Inject

class FundingCreateUseCase @Inject constructor(
    private val fundingRepository: FundingRepository
) {
    suspend operator fun invoke(fundingCreateParam: FundingCreateParam) = runCatching {
        fundingRepository.fundingCreate(fundingCreateParam = fundingCreateParam)
    }
}