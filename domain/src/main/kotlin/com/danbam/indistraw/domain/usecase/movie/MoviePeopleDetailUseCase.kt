package com.danbam.indistraw.domain.usecase.movie

import com.danbam.indistraw.domain.repository.MovieRepository
import javax.inject.Inject

class MoviePeopleDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(actorType: String, actorIdx: Long) = runCatching {
        movieRepository.moviePeopleDetail(actorType = actorType, actorIdx = actorIdx)
    }
}