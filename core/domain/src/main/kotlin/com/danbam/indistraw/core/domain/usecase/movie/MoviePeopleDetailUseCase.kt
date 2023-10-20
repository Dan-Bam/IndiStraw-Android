package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class MoviePeopleDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(peopleType: String, actorIdx: Long) = runCatching {
        movieRepository.moviePeopleDetail(peopleType = peopleType, actorIdx = actorIdx)
    }
}