package com.danbam.data.repository

import com.danbam.data.remote.datasource.MovieRemoteDataSource
import com.danbam.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
}