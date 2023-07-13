package com.danbam.domain.usecase.movie

import com.danbam.domain.param.MovieHistoryParam
import com.danbam.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieHistoryParam: MovieHistoryParam) = runCatching {
        movieRepository.addMovieHistory(movieHistoryParam = movieHistoryParam)
    }
}