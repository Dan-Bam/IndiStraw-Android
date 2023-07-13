package com.danbam.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.data.remote.datasource.MovieRemoteDataSource
import com.danbam.data.remote.request.toRequest
import com.danbam.data.remote.response.toEntity
import com.danbam.domain.entity.MovieDetailEntity
import com.danbam.domain.entity.MovieEntity
import com.danbam.domain.entity.MovieHistoryEntity
import com.danbam.domain.entity.MoviePeopleDetailEntity
import com.danbam.domain.entity.MoviePeopleEntity
import com.danbam.domain.param.MovieCreateParam
import com.danbam.domain.param.MovieHistoryParam
import com.danbam.domain.param.MoviePeopleParam
import com.danbam.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun movieCreate(movieCreateParam: MovieCreateParam) =
        movieRemoteDataSource.movieCreate(movieCreateRequest = movieCreateParam.toRequest())

    override suspend fun movieList(genre: String?): Flow<PagingData<MovieEntity>> =
        movieRemoteDataSource.movieList(genre = genre).map { it.map { it.toEntity() } }

    override suspend fun movieDetail(movieIdx: Int): MovieDetailEntity =
        movieRemoteDataSource.movieDetail(movieIdx = movieIdx).toEntity()

    override suspend fun searchMoviePeople(
        actorType: String,
        name: String
    ): List<MoviePeopleEntity> =
        movieRemoteDataSource.searchMoviePeople(actorType = actorType, name = name)
            .map { it.toEntity() }

    override suspend fun addMoviePeople(actorType: String, moviePeopleParam: MoviePeopleParam) =
        movieRemoteDataSource.addMoviePeople(
            actorType = actorType,
            moviePeopleRequest = moviePeopleParam.toRequest()
        ).actorIdx

    override suspend fun moviePeopleDetail(actorType: String, idx: Int): MoviePeopleDetailEntity =
        movieRemoteDataSource.moviePeopleDetail(actorType = actorType, idx = idx).toEntity()

    override suspend fun movieRecentList(): List<MovieEntity> =
        movieRemoteDataSource.movieRecentList().map { it.toEntity() }

    override suspend fun moviePopularList(): List<MovieEntity> =
        movieRemoteDataSource.moviePopularList().map { it.toEntity() }

    override suspend fun movieRecommendList(): List<MovieEntity> =
        movieRemoteDataSource.movieRecommendList().map { it.toEntity() }

    override suspend fun movieHistoryList(): List<MovieHistoryEntity> =
        movieRemoteDataSource.movieHistoryList().map { it.toEntity() }

    override suspend fun addMovieHistory(movieHistoryParam: MovieHistoryParam): MovieHistoryEntity =
        movieRemoteDataSource.addMovieHistory(movieHistoryRequest = movieHistoryParam.toRequest())
            .toEntity()
}