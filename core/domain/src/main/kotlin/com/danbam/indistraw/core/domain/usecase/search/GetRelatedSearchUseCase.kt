package com.danbam.indistraw.core.domain.usecase.search

import com.danbam.indistraw.core.domain.repository.SearchRepository
import javax.inject.Inject

class GetRelatedSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(keyword: String) = runCatching {
        searchRepository.getRelatedSearch(keyword = keyword)
    }
}