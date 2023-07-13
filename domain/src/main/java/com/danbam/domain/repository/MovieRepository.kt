package com.danbam.domain.repository

import androidx.paging.PagingData
import com.danbam.domain.entity.MovieDetailEntity
import com.danbam.domain.entity.MovieEntity
import com.danbam.domain.entity.MovieHistoryEntity
import com.danbam.domain.entity.MoviePeopleDetailEntity
import com.danbam.domain.entity.MoviePeopleEntity
import com.danbam.domain.param.MovieCreateParam
import com.danbam.domain.param.MovieHistoryParam
import com.danbam.domain.param.MoviePeopleParam
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun movieCreate(movieCreateParam: MovieCreateParam)
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieEntity>>
    suspend fun movieDetail(movieIdx: Int): MovieDetailEntity
    suspend fun searchMoviePeople(actorType: String, name: String): List<MoviePeopleEntity>
    suspend fun addMoviePeople(actorType: String, moviePeopleParam: MoviePeopleParam): Int
    suspend fun moviePeopleDetail(actorType: String, idx: Int): MoviePeopleDetailEntity
    suspend fun movieRecentList(): List<MovieEntity>
    suspend fun moviePopularList(): List<MovieEntity>
    suspend fun movieRecommendList(): List<MovieEntity>
    suspend fun movieHistoryList(): List<MovieHistoryEntity>
    suspend fun addMovieHistory(movieHistoryParam: MovieHistoryParam): MovieHistoryEntity
}