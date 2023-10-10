package com.danbam.domain.usecase.movie

import com.danbam.domain.repository.MovieRepository
import javax.inject.Inject

class MoviePopularListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke() = runCatching {
        movieRepository.moviePopularList()
    }
}