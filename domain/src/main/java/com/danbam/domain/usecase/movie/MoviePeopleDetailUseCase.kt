package com.danbam.domain.usecase.movie

import com.danbam.domain.repository.MovieRepository
import javax.inject.Inject

class MoviePeopleDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(actorType: String, idx: Long) = runCatching {
        movieRepository.moviePeopleDetail(actorType = actorType, idx = idx)
    }
}