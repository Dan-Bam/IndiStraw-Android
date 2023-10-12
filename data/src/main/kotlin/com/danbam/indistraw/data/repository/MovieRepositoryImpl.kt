package com.danbam.indistraw.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.danbam.indistraw.data.remote.datasource.MovieRemoteDataSource
import com.danbam.indistraw.data.remote.request.movie.toRequest
import com.danbam.indistraw.data.remote.response.movie.toEntity
import com.danbam.indistraw.domain.entity.movie.DetailMovieHistoryEntity
import com.danbam.indistraw.domain.entity.movie.MovieDetailEntity
import com.danbam.indistraw.domain.entity.movie.MovieEntity
import com.danbam.indistraw.domain.entity.movie.MovieHistoryEntity
import com.danbam.indistraw.domain.entity.movie.MoviePeopleDetailEntity
import com.danbam.indistraw.domain.entity.movie.MoviePeopleEntity
import com.danbam.indistraw.domain.param.movie.MovieCreateParam
import com.danbam.indistraw.domain.param.movie.MovieHistoryParam
import com.danbam.indistraw.domain.param.movie.MoviePeopleParam
import com.danbam.indistraw.domain.repository.MovieRepository
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

    override suspend fun movieDetail(movieIdx: Long): MovieDetailEntity =
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

    override suspend fun moviePeopleDetail(actorType: String, actorIdx: Long): MoviePeopleDetailEntity =
        movieRemoteDataSource.moviePeopleDetail(actorType = actorType, actorIdx = actorIdx).toEntity()

    override suspend fun movieRecentList(): List<MovieEntity> =
        movieRemoteDataSource.movieRecentList().map { it.toEntity() }

    override suspend fun moviePopularList(): List<MovieEntity> =
        movieRemoteDataSource.moviePopularList().map { it.toEntity() }

    override suspend fun movieRecommendList(): List<MovieEntity> =
        movieRemoteDataSource.movieRecommendList().map { it.toEntity() }

    override suspend fun movieHistoryList(): List<MovieHistoryEntity> =
        movieRemoteDataSource.movieHistoryList().map { it.toEntity() }

    override suspend fun addMovieHistory(movieHistoryParam: MovieHistoryParam) =
        movieRemoteDataSource.addMovieHistory(movieHistoryRequest = movieHistoryParam.toRequest())

    override suspend fun movieHistory(movieIdx: Long): DetailMovieHistoryEntity =
        movieRemoteDataSource.movieHistory(movieIdx = movieIdx).toEntity()
}