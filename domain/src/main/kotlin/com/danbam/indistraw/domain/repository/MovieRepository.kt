package com.danbam.indistraw.domain.repository

import androidx.paging.PagingData
import com.danbam.indistraw.domain.entity.movie.DetailMovieHistoryEntity
import com.danbam.indistraw.domain.entity.movie.MovieDetailEntity
import com.danbam.indistraw.domain.entity.movie.MovieEntity
import com.danbam.indistraw.domain.entity.movie.MovieHistoryEntity
import com.danbam.indistraw.domain.entity.movie.MoviePeopleDetailEntity
import com.danbam.indistraw.domain.entity.movie.MoviePeopleEntity
import com.danbam.indistraw.domain.param.movie.MovieCreateParam
import com.danbam.indistraw.domain.param.movie.MovieHistoryParam
import com.danbam.indistraw.domain.param.movie.MoviePeopleParam
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun movieCreate(movieCreateParam: MovieCreateParam)
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieEntity>>
    suspend fun movieDetail(movieIdx: Long): MovieDetailEntity
    suspend fun searchMoviePeople(actorType: String, name: String): List<MoviePeopleEntity>
    suspend fun addMoviePeople(actorType: String, moviePeopleParam: MoviePeopleParam): Long
    suspend fun moviePeopleDetail(actorType: String, actorIdx: Long): MoviePeopleDetailEntity
    suspend fun movieRecentList(): List<MovieEntity>
    suspend fun moviePopularList(): List<MovieEntity>
    suspend fun movieRecommendList(): List<MovieEntity>
    suspend fun movieHistoryList(): List<MovieHistoryEntity>
    suspend fun addMovieHistory(movieHistoryParam: MovieHistoryParam)
    suspend fun movieHistory(movieIdx: Long): DetailMovieHistoryEntity
}