package com.danbam.domain.usecase.movie

import com.danbam.domain.repository.MovieRepository
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieIndex: Int) = runCatching {
        movieRepository.movieDetail(movieIdx = movieIndex)
    }
}