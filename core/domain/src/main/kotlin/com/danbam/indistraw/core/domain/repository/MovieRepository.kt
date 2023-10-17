package com.danbam.indistraw.core.domain.repository

import androidx.paging.PagingData
import com.danbam.indistraw.core.domain.entity.movie.DetailMovieHistoryEntity
import com.danbam.indistraw.core.domain.entity.movie.MovieDetailEntity
import com.danbam.indistraw.core.domain.entity.movie.MovieEntity
import com.danbam.indistraw.core.domain.entity.movie.MovieHistoryEntity
import com.danbam.indistraw.core.domain.entity.movie.MoviePeopleDetailEntity
import com.danbam.indistraw.core.domain.entity.movie.MoviePeopleEntity
import com.danbam.indistraw.core.domain.param.movie.MovieCreateParam
import com.danbam.indistraw.core.domain.param.movie.MovieHistoryParam
import com.danbam.indistraw.core.domain.param.movie.MoviePeopleParam
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun movieCreate(movieCreateParam: MovieCreateParam)
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieEntity>>
    suspend fun movieDetail(movieIdx: Long): MovieDetailEntity
    suspend fun searchMoviePeople(peopleType: String, name: String): List<MoviePeopleEntity>
    suspend fun addMoviePeople(peopleType: String, moviePeopleParam: MoviePeopleParam): Long
    suspend fun moviePeopleDetail(peopleType: String, actorIdx: Long): MoviePeopleDetailEntity
    suspend fun movieRecentList(): List<MovieEntity>
    suspend fun moviePopularList(): List<MovieEntity>
    suspend fun movieRecommendList(): List<MovieEntity>
    suspend fun movieHistoryList(): List<MovieHistoryEntity>
    suspend fun addMovieHistory(movieHistoryParam: MovieHistoryParam)
    suspend fun movieHistory(movieIdx: Long): DetailMovieHistoryEntity
    suspend fun movieFilmography(): List<MovieEntity>
}