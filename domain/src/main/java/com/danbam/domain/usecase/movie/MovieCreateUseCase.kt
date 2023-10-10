package com.danbam.domain.usecase.movie

import com.danbam.domain.param.movie.MovieCreateParam
import com.danbam.domain.repository.MovieRepository
import javax.inject.Inject

class MovieCreateUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieCreateParam: MovieCreateParam) = runCatching {
        movieRepository.movieCreate(movieCreateParam = movieCreateParam)
    }
}