package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.domain.param.movie.MovieHistoryParam
import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieHistoryParam: MovieHistoryParam) = runCatching {
        movieRepository.addMovieHistory(movieHistoryParam = movieHistoryParam)
    }
}