package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.param.movie.MoviePeopleParam
import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class AddMoviePeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(actorType: String, moviePeopleParam: MoviePeopleParam) =
        runCatching {
            movieRepository.addMoviePeople(
                actorType = actorType,
                moviePeopleParam = moviePeopleParam
            )
        }
}