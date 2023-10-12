package com.danbam.indistraw.domain.usecase.movie

import com.danbam.indistraw.domain.param.movie.MovieCreateParam
import com.danbam.indistraw.domain.repository.MovieRepository
import javax.inject.Inject

class MovieCreateUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieCreateParam: MovieCreateParam) = runCatching {
        movieRepository.movieCreate(movieCreateParam = movieCreateParam)
    }
}