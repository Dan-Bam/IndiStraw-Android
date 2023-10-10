package com.danbam.domain.usecase.movie

import com.danbam.domain.param.movie.MoviePeopleParam
import com.danbam.domain.repository.MovieRepository
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