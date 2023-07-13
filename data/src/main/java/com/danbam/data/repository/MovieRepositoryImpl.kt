package com.danbam.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.data.remote.datasource.MovieRemoteDataSource
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.entity.MovieEntity
import com.danbam.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun movieList(genre: String?): Flow<PagingData<MovieEntity>> =
        movieRemoteDataSource.movieList(genre = genre).map { it.map { it.toEntity() } }
}