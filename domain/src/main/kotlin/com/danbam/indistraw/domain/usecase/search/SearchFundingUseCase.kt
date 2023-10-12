package com.danbam.indistraw.domain.usecase.search

import com.danbam.indistraw.domain.entity.search.RecentSearchEntity
import com.danbam.indistraw.domain.repository.SearchRepository
import javax.inject.Inject

class SearchFundingUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(recentSearchEntity: RecentSearchEntity) = runCatching {
        searchRepository.searchFunding(recentSearchEntity = recentSearchEntity)
    }
}