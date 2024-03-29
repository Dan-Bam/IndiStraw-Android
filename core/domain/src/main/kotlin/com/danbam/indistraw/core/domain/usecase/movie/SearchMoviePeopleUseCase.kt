package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviePeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(peopleType: String, name: String) = runCatching {
        movieRepository.searchMoviePeople(peopleType = peopleType, name = name)
    }
}