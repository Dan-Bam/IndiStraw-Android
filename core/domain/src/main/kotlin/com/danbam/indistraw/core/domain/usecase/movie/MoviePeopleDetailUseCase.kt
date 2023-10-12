package com.danbam.indistraw.core.domain.usecase.movie

import com.danbam.indistraw.core.domain.repository.MovieRepository
import javax.inject.Inject

class MoviePeopleDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(actorType: String, actorIdx: Long) = runCatching {
        movieRepository.moviePeopleDetail(actorType = actorType, actorIdx = actorIdx)
    }
}