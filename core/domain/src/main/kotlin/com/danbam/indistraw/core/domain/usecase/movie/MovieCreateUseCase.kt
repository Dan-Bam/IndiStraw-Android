package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.domain.param.movie.MovieCreateParam
import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class MovieCreateUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieCreateParam: MovieCreateParam) = runCatching {
        movieRepository.movieCreate(movieCreateParam = movieCreateParam)
    }
}