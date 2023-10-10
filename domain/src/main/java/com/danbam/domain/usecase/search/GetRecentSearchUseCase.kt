package com.danbam.domain.usecase.search

import com.danbam.domain.repository.SearchRepository
import javax.inject.Inject

class GetRecentSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke() = runCatching {
        searchRepository.getRecentSearch()
    }
}