package com.danbam.indistraw.domain.usecase.movie

import com.danbam.indistraw.domain.param.movie.MovieHistoryParam
import com.danbam.indistraw.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieHistoryParam: MovieHistoryParam) = runCatching {
        movieRepository.addMovieHistory(movieHistoryParam = movieHistoryParam)
    }
}