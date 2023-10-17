package com.danbam.indistraw.core.domain.usecase.search

import com.danbam.indistraw.core.domain.entity.search.RecentSearchEntity
import com.danbam.indistraw.core.domain.repository.SearchRepository
import javax.inject.Inject

class SearchFundingUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(recentSearchEntity: RecentSearchEntity) = runCatching {
        searchRepository.searchFunding(recentSearchEntity = recentSearchEntity)
    }
}