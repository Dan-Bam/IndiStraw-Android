package com.danbam.domain.usecase.movie

import com.danbam.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviePeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(actorType: String, name: String) = runCatching {
        movieRepository.searchMoviePeople(actorType = actorType, name = name)
    }
}