package com.danbam.domain.usecase.search

import com.danbam.domain.entity.RecentSearchEntity
import com.danbam.domain.repository.SearchRepository
import javax.inject.Inject

class SearchFundingUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(recentSearchEntity: RecentSearchEntity) = runCatching {
        searchRepository.searchFunding(recentSearchEntity = recentSearchEntity)
    }
}