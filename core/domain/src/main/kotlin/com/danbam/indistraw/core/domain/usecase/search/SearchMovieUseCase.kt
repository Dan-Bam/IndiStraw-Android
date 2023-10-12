package com.danbam.indistraw.core.domain.usecase.search

import com.danbam.indistraw.core.entity.search.RecentSearchEntity
import com.danbam.indistraw.core.domain.repository.SearchRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(recentSearchEntity: RecentSearchEntity) = runCatching {
        searchRepository.searchMovie(recentSearchEntity = recentSearchEntity)
    }
}