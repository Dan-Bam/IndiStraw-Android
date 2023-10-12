package com.danbam.indistraw.domain.usecase.movie

import com.danbam.indistraw.domain.repository.MovieRepository
import javax.inject.Inject

class MovieHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieIdx: Long) = runCatching {
        movieRepository.movieHistory(movieIdx = movieIdx)
    }
}