package com.danbam.domain.usecase.search

import com.danbam.domain.entity.search.RecentSearchEntity
import com.danbam.domain.repository.SearchRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(recentSearchEntity: RecentSearchEntity) = runCatching {
        searchRepository.searchMovie(recentSearchEntity = recentSearchEntity)
    }
}