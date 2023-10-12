package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class MoviePopularListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke() = runCatching {
        movieRepository.moviePopularList()
    }
}