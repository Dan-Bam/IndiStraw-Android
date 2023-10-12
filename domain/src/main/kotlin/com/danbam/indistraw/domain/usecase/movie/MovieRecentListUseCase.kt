package com.danbam.indistraw.domain.usecase.movie

import com.danbam.indistraw.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRecentListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke() = runCatching {
        movieRepository.movieRecentList()
    }
}