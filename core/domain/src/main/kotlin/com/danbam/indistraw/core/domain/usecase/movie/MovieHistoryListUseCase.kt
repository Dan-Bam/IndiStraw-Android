package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class MovieHistoryListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke() = runCatching {
        movieRepository.movieHistoryList()
    }
}